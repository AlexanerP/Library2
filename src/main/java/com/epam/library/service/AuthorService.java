package com.epam.library.service;

import com.epam.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    boolean create(String name) throws ServiceException;

    int update(String authorId, String name) throws ServiceException;

    int getCountAuthors() throws ServiceException;

    int getCountBooksByAuthors(String name) throws ServiceException;

    Optional<Author> showAuthorByName(String name) throws ServiceException;

    Optional<Author> showAuthorById(String authorId) throws ServiceException;

    List<Author> showAllAuthors() throws ServiceException;
}
