package com.epam.library.dao;

import com.epam.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {

    boolean create(Author author) throws DAOException;

    boolean updateById(Author author) throws DAOException;

    boolean deleteAuthorByBookId(long bookId) throws DAOException;

    Optional<Author> getAuthorByName(String name) throws DAOException;

    List<Author> getAuthorsByIdBook(Long bookId) throws DAOException;

    List<Author> getAuthors() throws DAOException;

    List<Author> getAllAuthorByPartName(String partName);

    int getCount() throws DAOException;

}
