package com.epam.library.dao;

import com.epam.library.entity.Book;

import java.util.Optional;

public interface BookDao {

    int update(Book book) throws DAOException;

    int updateBorrow(long bookId, int borrow) throws DAOException;

    Optional<Book> getBookById(Long bookId) throws DAOException;

    long getCountBooks() throws DAOException;

}
