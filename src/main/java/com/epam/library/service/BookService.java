package com.epam.library.service;

import com.epam.library.entity.Book;

import java.util.Optional;

public interface BookService {

    long getCountBooks() throws ServiceException;

    int update(String bookId, Book book, String quantity, String cityLibrary) throws ServiceException;

    boolean addBorrow(String bookId) throws ServiceException ;

    boolean deleteBorrow(String bookId) throws ServiceException ;

    Optional<Book> showBookById(String bookId) throws ServiceException;

}
