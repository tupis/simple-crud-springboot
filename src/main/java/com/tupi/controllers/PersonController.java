package com.tupi.controllers;

import com.tupi.data.vo.v1.BooksVO;
import com.tupi.data.vo.v1.PersonVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonController {
    List<PersonVO> findAll();
    PersonVO findById(Long id);
    PersonVO create(PersonVO person);
    PersonVO update(Long id, PersonVO person);
    ResponseEntity<String> deleteById(Long id);
}
