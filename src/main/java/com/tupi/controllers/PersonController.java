package com.tupi.controllers;

import com.tupi.data.vo.v1.PersonVO;
import com.tupi.services.PersonServices;
import com.tupi.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable("id") Long id) {
      return service.findById(id);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO PersonVO) {
        LogUtil<PersonVO> logUtil = new LogUtil<>();
        logUtil.logTudo(PersonVO);
        return service.create(PersonVO);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@PathVariable("id") Long id, @RequestBody PersonVO PersonVO) {
        return service.update(id, PersonVO);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
