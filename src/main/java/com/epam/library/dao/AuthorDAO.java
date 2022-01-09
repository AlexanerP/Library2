package com.epam.library.dao;

import com.epam.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {

    boolean create(Author author) throws DAOException;

    boolean update(Author author) throws DAOException;

    boolean deleteAuthorByBookId(long bookId) throws DAOException;

    Optional<Author> getAuthorByName(String name) throws DAOException;

    Optional<Author> getAuthorById(long authorId) throws DAOException;

    int getCountAuthors() throws DAOException;

    int getCountBooksByAuthor(String author) throws DAOException;

    List<Author> getAuthors() throws DAOException;

    List<Author> getAllAuthorByPartName(String partName);

}
