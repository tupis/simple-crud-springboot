package com.tupi.services;

import com.tupi.controllers.PersonController;
import com.tupi.data.vo.v1.PersonVO;
import com.tupi.exceptions.ResourceNotFoundException;
import com.tupi.mapper.DozerMapper;
import com.tupi.models.Person;
import com.tupi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonVO findById(Long id) {
        logger.info("Searching for one person...");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonVO vo = DozerMapper.parseObject(person, PersonVO.class);

        vo.add(
                linkTo(
                        methodOn(PersonController.class).findById(id)
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
                            methodOn(PersonController.class).findById(p.getKey())
                    ).withSelfRel()
                )
        );

        return vos;
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one person");
        Person person = DozerMapper.parseObject(personVO, Person.class);
        person.setId(null);
        Person savedPerson = repository.save(person);

        PersonVO vo = DozerMapper.parseObject(savedPerson, PersonVO.class);

        vo.add(
                linkTo(
                        methodOn(PersonController.class).findById(savedPerson.getId())
                ).withSelfRel()
        );

        return vo;
    }

    public PersonVO update(Long id, PersonVO personVO) {
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
                        methodOn(PersonController.class).findById(savedPerson.getId())
                ).withSelfRel()
        );

        return vo;
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "Person deleted!";
    }

}
