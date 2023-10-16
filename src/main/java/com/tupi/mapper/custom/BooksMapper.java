package com.tupi.mapper.custom;

import com.tupi.data.vo.v1.BooksVO;
import com.tupi.models.Books;

import java.util.List;
import java.util.stream.Collectors;

public class BooksMapper {
    public static BooksVO parserBookToVO(Books entity) {
        BooksVO vo = new BooksVO();
        vo.setKey(entity.getId());
        vo.setAuthor(entity.getAuthor());
        vo.setLaunchDate(entity.getLaunchDate());
        vo.setPrice(entity.getPrice());
        vo.setTitle(entity.getTitle());
        return vo;
    }

    public static Books parserVOToBook(BooksVO vo) {
        Books entity = new Books();
        entity.setId(vo.getKey());
        entity.setAuthor(vo.getAuthor());
        entity.setLaunchDate(vo.getLaunchDate());
        entity.setPrice(vo.getPrice());
        entity.setTitle(vo.getTitle());
        return entity;
    }

    public List<BooksVO> parserListBooksToVO(List<Books> entity) {
        return entity.stream().map(BooksMapper::parserBookToVO).collect(Collectors.toList());
    }

    public List<Books> parserListVOToBooks(List<BooksVO> vo) {
        return vo.stream().map(BooksMapper::parserVOToBook).collect(Collectors.toList());
    }

}
