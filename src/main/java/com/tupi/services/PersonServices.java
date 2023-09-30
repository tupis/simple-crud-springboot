package com.tupi.services;

import com.tupi.data.vo.v1.PersonVO;
import com.tupi.exceptions.ResourceNotFoundException;
import com.tupi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger  = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonVO findById(Long id) {
        logger.info("Finding one PersonVO!");
        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public List<PersonVO> findAll() {
        return repository.findAll();
    }

    public PersonVO create(PersonVO PersonVO) {
        PersonVO.setId(null);
        return repository.save(PersonVO);
    }

    public PersonVO update(Long id, PersonVO PersonVO) {
        PersonVO entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        entity.setAddress(PersonVO.getAddress());
        entity.setFirstName(PersonVO.getFirstName());
        entity.setLastName(PersonVO.getLastName());
        entity.setGender(PersonVO.getGender());

        return repository.save(entity);
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "PersonVO deleted!";
    }

}
