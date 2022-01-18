package com.epam.library.service;

import com.epam.library.entity.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDtoService {

    boolean create(String title, String isbn, String publisher, String year, String count, String city,
                   String shelf, String author, String category, String description) throws ServiceException;

    int update(String bookId, BookDto bookDto, String author, String genre, String quantity) throws ServiceException;

    List<BookDto> showCatalog() throws ServiceException;

    Optional<BookDto> showBookById(String bookId) throws ServiceException;

    List<BookDto> showBookByCity(String city) throws ServiceException;

    List<BookDto> showBookByParameter(String title, String isbn, String genre, String author) throws ServiceException;

    List<BookDto> showByPage(int page, int limit) throws ServiceException;
}
