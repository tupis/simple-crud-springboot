package com.tupi.unittests.mockito.services;

import com.tupi.data.vo.v1.BooksVO;
import com.tupi.mapper.DozerMapper;
import com.tupi.models.Books;
import com.tupi.repositories.BooksRepository;
import com.tupi.services.impl.BooksServiceImpl;
import com.tupi.unittests.mapper.mocks.MockBooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BooksServiceImplTest {
    MockBooks input;
    @Mock
    BooksRepository repository;

    @InjectMocks
    private BooksServiceImpl services;

    @BeforeEach
    void setUpMocks() {
        input = new MockBooks();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(input.mockEntityList());

        var result = services.findAll();

        assertNotNull(result);
        assertEquals(14, result.size());

        int numberTested = 0;
        for (BooksVO vo : result) {
            assertNotNull(vo.getKey());
            assertNotNull(vo.getAuthor());
            assertNotNull(vo.getLaunchDate());
            assertNotNull(vo.getPrice());
            assertNotNull(vo.getTitle());

            assertEquals(numberTested, vo.getKey());
            assertEquals("Author Name" + numberTested, vo.getAuthor());
            assertEquals("Title" + numberTested, vo.getTitle());
            assertEquals(numberTested, vo.getPrice());

            numberTested++;
        }
    }

    @Test
    void findById() {
        Books entity = input.mockEntity();
        when(repository.findById(0L)).thenReturn(Optional.of(entity));

        BooksVO result = services.findById(0L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());

        int numberTested = 0;

        assertEquals(numberTested, result.getKey());
        assertEquals("Author Name" + numberTested, result.getAuthor());
        assertEquals("Title" + numberTested, result.getTitle());
        assertEquals(numberTested, result.getPrice());

    }

    @Test
    void create() {
        Books inputEntity = input.mockEntity();
        BooksVO inputVo = input.mockVO();
        Books mapperEntity = DozerMapper.parseObject(inputVo, Books.class);

        when(repository.save(mapperEntity)).thenReturn(inputEntity);
        BooksVO result = services.create(inputVo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());

        int numberTested = 0;

        assertEquals(numberTested, result.getKey());
        assertEquals("Author Name" + numberTested, result.getAuthor());
        assertEquals("Title" + numberTested, result.getTitle());
        assertEquals(numberTested, result.getPrice());
    }

    @Test
    void update() {
        Books inputEntity = input.mockEntity();
        BooksVO inputVo = input.mockVO();
        Books mapperEntity = DozerMapper.parseObject(inputVo, Books.class);

        when(repository.save(mapperEntity)).thenReturn(inputEntity);
        BooksVO result = services.update(inputVo.getKey() ,inputVo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertNotNull(result.getPrice());
        assertNotNull(result.getTitle());

        int numberTested = 0;

        assertEquals(numberTested, result.getKey());
        assertEquals("Author Name" + numberTested, result.getAuthor());
        assertEquals("Title" + numberTested, result.getTitle());
        assertEquals(numberTested, result.getPrice());
    }

    @Test
    void deleteById() {
        Long id = 0L;
        services.deleteById(id);
    }
}