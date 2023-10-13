package com.tupi.services.impl;

import com.tupi.controllers.impl.PersonControllerImpl;
import com.tupi.data.vo.v1.PersonVO;
import com.tupi.exceptions.PersonNotNullException;
import com.tupi.exceptions.ResourceNotFoundException;
import com.tupi.mapper.DozerMapper;
import com.tupi.models.Person;
import com.tupi.repositories.PersonRepository;
import com.tupi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServicesImpl implements PersonService {
    private final Logger logger = Logger.getLogger(PersonServicesImpl.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonVO findById(Long id) {
        logger.info("Searching for one person...");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonVO vo = DozerMapper.parseObject(person, PersonVO.class);

        vo.add(
                linkTo(
                        methodOn(PersonControllerImpl.class).findById(id)
                ).withSelfRel()
        );

        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Searching for all persons");
        List<Person> persons = repository.findAll();
        List<PersonVO> vos = DozerMapper.parseListObjects(persons, PersonVO.class);

        vos.stream().forEach(p ->
                p.add(
                    linkTo(
                            methodOn(PersonControllerImpl.class).findById(p.getKey())
                    ).withSelfRel()
                )
        );

        return vos;
    }

    public PersonVO create(PersonVO personVO) throws PersonNotNullException {
        logger.info("Creating one person");

        if(personVO == null) throw new PersonNotNullException();

        Person person = DozerMapper.parseObject(personVO, Person.class);
        Person savedPerson = repository.save(person);

        PersonVO vo = DozerMapper.parseObject(savedPerson, PersonVO.class);

        vo.add(
                linkTo(
                        methodOn(PersonControllerImpl.class).findById(savedPerson.getId())
                ).withSelfRel()
        );

        return vo;
    }

    public PersonVO update(Long id, PersonVO personVO) throws PersonNotNullException {

        if(id == null) throw new PersonNotNullException("Please send a valid ID");

        if(personVO == null) throw new PersonNotNullException();

        logger.info("Updating one person");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        entity.setAddress(personVO.getAddress());
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setGender(personVO.getGender());
        Person savedPerson = repository.save(entity);

        PersonVO vo = DozerMapper.parseObject(savedPerson, PersonVO.class);

        vo.add(
                linkTo(
                        methodOn(PersonControllerImpl.class).findById(savedPerson.getId())
                ).withSelfRel()
        );

        return vo;
    }

    public ResponseEntity<String> deleteById(Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().body("Person deleted with success!");
    }

}
