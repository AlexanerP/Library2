package com.epam.library.service;

import com.epam.library.entity.dto.BookDto;

import java.util.List;

public interface BookDtoService {

    List<BookDto> showCatalog() throws ServiceException;

    BookDto showBook(long id) throws ServiceException;

    List<BookDto> showBookByParameter(String title, String isbn, String genre) throws ServiceException;

    int showCountBook() throws ServiceException;

    List<BookDto> showByPage(int page) throws ServiceException;
}
