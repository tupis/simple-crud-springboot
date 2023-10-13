package com.tupi.controllers.impl;

import com.tupi.controllers.BooksController;
import com.tupi.data.vo.v1.BooksVO;
import com.tupi.services.impl.BooksServiceImpl;
import com.tupi.utils.LogUtil;
import com.tupi.utils.MediaType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "endpoint to manager books")
public class BooksControllerImpl implements BooksController {

    @Autowired
    private BooksServiceImpl service;

    @Override
    @GetMapping("/findAll")
    public List<BooksVO> findAll() {
        return service.findAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BooksVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }


    @Override
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    public BooksVO create(@RequestBody BooksVO books) {
        return service.create(books);
    }

    @Override
    @PutMapping(
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    public BooksVO update(Long id, BooksVO books) {
        return null;
    }

    @Override
    @DeleteMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}
