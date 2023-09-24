package com.tupi.controllers;

import com.tupi.models.Person;
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
    public Person findById(@PathVariable("id") Long id) {
      return service.findById(id);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return service.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {
        LogUtil<Person> logUtil = new LogUtil<>();
        logUtil.logTudo(person);
        return service.create(person);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@PathVariable("id") Long id, @RequestBody Person person) {
        return service.update(id, person);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
