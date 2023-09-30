package com.tupi.services;

import com.tupi.data.vo.v1.PersonVO;
import com.tupi.data.vo.v2.PersonVO2;
import com.tupi.exceptions.ResourceNotFoundException;
import com.tupi.mapper.DozerMapper;
import com.tupi.mapper.custom.PersonMapper;
import com.tupi.models.Person;
import com.tupi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;


    @Autowired
    private PersonMapper mapper;

    public PersonVO findById(Long id) {
        logger.info("Searching for one person...");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(person, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Searching for all persons");
        List<Person> persons = repository.findAll();
        return DozerMapper.parseListObjects(persons, PersonVO.class);
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one person");
        Person person = DozerMapper.parseObject(personVO, Person.class);
        person.setId(null);
        Person savedPerson = repository.save(person);

        return DozerMapper.parseObject(savedPerson, PersonVO.class);
    }

    public PersonVO2 createV2(PersonVO2 personVO) {
        logger.info("Creating one person in V2");
        Person person = mapper.convertVOToEntity(personVO);
        person.setId(null);
        Person savedPerson = repository.save(person);

        return mapper.convertEntityToVO(savedPerson);
    }

    public PersonVO update(Long id, PersonVO personVO) {
        logger.info("Updating one person");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        entity.setAddress(personVO.getAddress());
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setGender(personVO.getGender());
        Person savedPerson = repository.save(entity);

        return DozerMapper.parseObject(savedPerson, PersonVO.class);
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "Person deleted!";
    }

}
