package com.tupi.services.impl;

import com.tupi.controllers.impl.BooksControllerImpl;
import com.tupi.data.vo.v1.BooksVO;
import com.tupi.exceptions.BooksNotFoundException;
import com.tupi.exceptions.BooksNotNullException;
import com.tupi.mapper.DozerMapper;
import com.tupi.mapper.custom.BooksMapper;
import com.tupi.models.Books;
import com.tupi.repositories.BooksRepository;
import com.tupi.services.BooksService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository repository;

    public List<BooksVO> findAll() {
        List<Books> books = repository.findAll();
        List<BooksVO> booksVO = DozerMapper.parseListObjects(books, BooksVO.class);

        booksVO.stream().forEach(book -> {
            book.add(
                    linkTo(
                            methodOn(BooksControllerImpl.class).findById(book.getKey())
                    ).withSelfRel()
            );
        });

        return booksVO;
    }

    public BooksVO findById(@NotNull Long id) {
        Books books = repository.findById(id).orElseThrow(() -> new BooksNotFoundException("no content found"));
        BooksVO booksVO = DozerMapper.parseObject(books, BooksVO.class);

        booksVO.add(
                linkTo(
                        methodOn(BooksControllerImpl.class).findById(id)
                ).withSelfRel()
        );

        return booksVO;
    }

    @Override
    public BooksVO create(BooksVO books) {
        if(books == null) throw new BooksNotNullException();

        Books entity = BooksMapper.parserVOToBook(books);

        Books savedBook = repository.save(entity);

        BooksVO booksVO = BooksMapper.parserBookToVO(savedBook);

        booksVO.add(
                linkTo(
                        methodOn(BooksControllerImpl.class).findById(booksVO.getKey())
                ).withSelfRel()
        );

        return booksVO;
    }

    public BooksVO update(Long id, BooksVO books) {
        Books entity = new Books();
        entity.setId(books.getKey());
        entity.setAuthor(books.getAuthor());
        entity.setLaunchDate(books.getLaunchDate());
        entity.setPrice(books.getPrice());
        entity.setTitle(books.getTitle());

        Books savedBook = repository.save(entity);

        BooksVO booksVO = DozerMapper.parseObject(savedBook, BooksVO.class);

        booksVO.add(
                linkTo(
                        methodOn(BooksControllerImpl.class).findById(id)
                ).withSelfRel()
        );

        return booksVO;
    }

    public ResponseEntity<String> deleteById(@NotNull Long id) {
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted with success");
    }
}
