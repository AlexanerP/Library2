package com.epam.library.dao;

import com.epam.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    boolean create(Author author) throws DaoException;

    boolean update(Author author) throws DaoException;

    boolean deleteAuthorByBookId(long bookId) throws DaoException;

    Optional<Author> getAuthorByName(String name) throws DaoException;

    Optional<Author> getAuthorById(long authorId) throws DaoException;

    int getCountAuthors() throws DaoException;

    int getCountBooksByAuthor(String author) throws DaoException;

    List<Author> getAuthors() throws DaoException;

    List<Author> getAllAuthorByPartName(String partName);

}
