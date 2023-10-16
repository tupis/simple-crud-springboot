package com.tupi.unittests.mapper.mocks;

import com.tupi.data.vo.v1.BooksVO;
import com.tupi.models.Books;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBooks {


    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksVO mockVO() {
        return mockVO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksVO> mockVOList() {
        List<BooksVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public Books mockEntity(Integer number) {
        Books books = new Books();
        books.setId(number.longValue());
        books.setAuthor("Author Name" + number);
        books.setLaunchDate(new Date());
        books.setPrice(number.doubleValue());
        books.setTitle("Title" + number);
        return books;
    }

    public BooksVO mockVO(Integer number) {
        BooksVO books = new BooksVO();
        books.setKey(number.longValue());
        books.setAuthor("Author Name" + number);
        books.setLaunchDate(new Date());
        books.setPrice(number.doubleValue());
        books.setTitle("Title" + number);
        return books;
    }

}
