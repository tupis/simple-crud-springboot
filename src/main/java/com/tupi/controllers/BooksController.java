package com.tupi.controllers;

import com.tupi.data.vo.v1.BooksVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BooksController {
    List<BooksVO> findAll();
    BooksVO findById(Long id);
    BooksVO create(BooksVO books);
    BooksVO update(Long id, BooksVO books);
    ResponseEntity<String> deleteById(Long id);
}

