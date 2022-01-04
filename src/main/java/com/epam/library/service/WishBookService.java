package com.epam.library.service;

import com.epam.library.entity.dto.WishBookDto;

import java.util.List;

public interface WishBookService {

    boolean add(String userId, String bookId) throws ServiceException;

    boolean delete(String wishBookId) throws ServiceException;

    long showCountBooks() throws ServiceException;
}
