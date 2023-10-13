package com.tupi.services;

import com.tupi.data.vo.v1.BooksVO;
import com.tupi.models.Books;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BooksService {
    List<BooksVO> findAll();
    BooksVO findById(Long id);
    BooksVO create(BooksVO books);
    BooksVO update(Long id, BooksVO bookss);
    ResponseEntity<String> deleteById(Long id);

}
