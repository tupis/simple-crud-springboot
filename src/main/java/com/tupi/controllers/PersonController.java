package com.tupi.controllers;

import com.tupi.data.vo.v1.PersonVO;
import com.tupi.services.PersonServices;
import com.tupi.utils.LogUtil;
import com.tupi.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    public PersonVO findById(@PathVariable("id") Long id) {
      return service.findById(id);
    }

    @GetMapping(
            value = "/findAll",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    public PersonVO create(@RequestBody PersonVO PersonVO) {
        LogUtil<PersonVO> logUtil = new LogUtil<>();
        logUtil.logTudo(PersonVO);
        return service.create(PersonVO);
    }

    @PutMapping(
            value = "{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    public PersonVO update(@PathVariable("id") Long id, @RequestBody PersonVO PersonVO) {
        return service.update(id, PersonVO);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
