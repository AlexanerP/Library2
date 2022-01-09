package com.epam.library.dao;

import com.epam.library.entity.dto.BookDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookDtoDao {

    boolean create(BookDto bookDto) throws DAOException;

    boolean update(BookDto bookDto) throws DAOException;

    List<BookDto> getBookByIsbn(String isbn) throws DAOException;

    Optional<BookDto> getBookById(long bookId) throws DAOException;

    int delete(long id) throws DAOException;

    List<BookDto> getBooks() throws DAOException;

    List<BookDto> getBooksByCity(String city) throws DAOException;

    List<BookDto> getBooksByPage(int limit, int page) throws DAOException;

    List<BookDto> getBooksByTitle(String title) throws DAOException;

    List<BookDto> getBooksByAuthor(String author) throws DAOException;

    List<BookDto> getBooksByGenre(String genre) throws DAOException;

    List<BookDto> getCountBooksByPeriod(LocalDate start, LocalDate finish);
}
