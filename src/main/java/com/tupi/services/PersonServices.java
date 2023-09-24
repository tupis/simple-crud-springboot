package com.tupi.services;

import com.tupi.exceptions.ResourceNotFoundException;
import com.tupi.models.Person;
import com.tupi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger  = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person create(Person person) {
        person.setId(null);
        return repository.save(person);
    }

    public Person update(Long id, Person person) {
        Person entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "Person deleted!";
    }

}
