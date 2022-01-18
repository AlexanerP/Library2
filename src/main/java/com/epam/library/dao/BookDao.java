package com.epam.library.dao;

import com.epam.library.entity.Book;

import java.util.Optional;

public interface BookDao {

    int update(Book book) throws DaoException;

    Optional<Book> getBookById(Long bookId) throws DaoException;

    long getCountBooks() throws DaoException;

}
