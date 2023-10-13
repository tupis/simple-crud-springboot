package com.tupi.unittests.mockito.services;

import com.tupi.data.vo.v1.PersonVO;
import com.tupi.exceptions.PersonNotNullException;
import com.tupi.models.Person;
import com.tupi.repositories.PersonRepository;
import com.tupi.services.impl.PersonServicesImpl;
import com.tupi.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesImplTest {
    MockPerson input;

    @Mock
    PersonRepository repository;

    @InjectMocks
    private PersonServicesImpl services;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        PersonVO result = services.findById(1L);

        assertNotNull(result.getKey());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getAddress());
        assertNotNull(result.getGender());
        assertNotNull(result.getLinks());

        assertEquals(0, result.getKey());
        assertEquals("First Name Test" + 0, result.getFirstName());
        assertEquals("Last Name Test" + 0, result.getLastName());
        assertEquals("Male", result.getGender());
        assertEquals("Addres Test" + 0, result.getAddress());

    }

    @Test
    void findAll() {
        List<Person> peoples = input.mockEntityList();
        when(repository.findAll()).thenReturn(peoples);

        List<PersonVO> result = services.findAll();

        PersonVO personOne = result.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getFirstName());
        assertNotNull(personOne.getLastName());
        assertNotNull(personOne.getAddress());
        assertNotNull(personOne.getGender());
        assertNotNull(personOne.getLinks());

        assertEquals(1, personOne.getKey());
        assertEquals("First Name Test" + 1, personOne.getFirstName());
        assertEquals("Last Name Test" + 1, personOne.getLastName());
        assertEquals("Addres Test" + 1, personOne.getAddress());
        assertEquals("Female", personOne.getGender());


        PersonVO personFive = result.get(5);
        assertNotNull(personFive);
        assertNotNull(personFive.getKey());
        assertNotNull(personFive.getFirstName());
        assertNotNull(personFive.getLastName());
        assertNotNull(personFive.getAddress());
        assertNotNull(personFive.getGender());
        assertNotNull(personFive.getLinks());

        assertEquals(5, personFive.getKey());
        assertEquals("First Name Test" + 5, personFive.getFirstName());
        assertEquals("Last Name Test" + 5, personFive.getLastName());
        assertEquals("Addres Test" + 5, personFive.getAddress());
        assertEquals("Female", personFive.getGender());


        PersonVO personTwelve = result.get(12);
        assertNotNull(personTwelve);
        assertNotNull(personTwelve.getKey());
        assertNotNull(personTwelve.getFirstName());
        assertNotNull(personTwelve.getLastName());
        assertNotNull(personTwelve.getAddress());
        assertNotNull(personTwelve.getGender());
        assertNotNull(personTwelve.getLinks());

        assertEquals(12, personTwelve.getKey());
        assertEquals("First Name Test" + 12, personTwelve.getFirstName());
        assertEquals("Last Name Test" + 12, personTwelve.getLastName());
        assertEquals("Addres Test" + 12, personTwelve.getAddress());
        assertEquals("Male", personTwelve.getGender());
    }

    @Test
    void create() {
        Person person = input.mockEntity();
        PersonVO vo = input.mockVO();

        when(repository.save(person)).thenReturn(person);
        PersonVO result = services.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getAddress());
        assertNotNull(result.getGender());
        assertNotNull(result.getLinks());

        assertEquals(0, result.getKey());
        assertEquals("First Name Test" + 0, result.getFirstName());
        assertEquals("Last Name Test" + 0, result.getLastName());
        assertEquals("Male", result.getGender());
        assertEquals("Addres Test" + 0, result.getAddress());
    }

    @Test
    void createWithNullPerson() {
       Exception exception = assertThrows(PersonNotNullException.class, () -> {
           services.create(null);
       });

       String expectedMessage = "Person object does not exist. Please create a person object first.";
       assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void update() {
        Person person = input.mockEntity();
        PersonVO vo = input.mockVO();

        when(repository.findById(person.getId())).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(person);
        PersonVO result = services.update(person.getId(), vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertNotNull(result.getAddress());
        assertNotNull(result.getGender());
        assertNotNull(result.getLinks());

        assertEquals(0, result.getKey());
        assertEquals("First Name Test" + 0, result.getFirstName());
        assertEquals("Last Name Test" + 0, result.getLastName());
        assertEquals("Male", result.getGender());
        assertEquals("Addres Test" + 0, result.getAddress());
    }

    @Test
    void updateWithNullPerson() {
        Exception exception = assertThrows(PersonNotNullException.class, () -> {
            services.update(1L, null);
        });

        String expectedMessage = "Person object does not exist. Please create a person object first.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateWithIDNullPerson() {
        Exception exception = assertThrows(PersonNotNullException.class, () -> {
            services.update(null, null);
        });

        String expectedMessage = "Please send a valid ID";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void delete() {
    }
}