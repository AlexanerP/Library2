package com.epam.library.service;

import com.epam.library.entity.Book;

public interface BookService {

    long getCountBooks() throws ServiceException;

    boolean update(String bookId, Book book, String quantity, String cityLibrary) throws ServiceException;

    boolean updateBorrow(String bookId) throws ServiceException ;

}
