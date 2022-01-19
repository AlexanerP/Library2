package com.epam.library.dao;

import com.epam.library.entity.dto.BookDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookDtoDao {

    boolean create(BookDto bookDto) throws DaoException;

    boolean update(BookDto bookDto) throws DaoException;

    List<BookDto> getBookByIsbn(String isbn) throws DaoException;

    Optional<BookDto> getBookById(long bookId) throws DaoException;

    int delete(long id) throws DaoException;

    List<BookDto> getBooks() throws DaoException;

    List<BookDto> getBooksByCity(String city) throws DaoException;

    List<BookDto> getBooksByPage(int limit, int page) throws DaoException;

    List<BookDto> getBooksByTitle(String title) throws DaoException;

    List<BookDto> getBooksByAuthor(String author) throws DaoException;

    List<BookDto> getBooksByGenre(String genre) throws DaoException;

}
