package com.epam.library.dao;

import com.epam.library.entity.Genre;

import java.util.List;

public interface GenreDAO {

    boolean create(Genre genre) throws DAOException;

    boolean update(Genre author) throws DAOException;

    boolean deleteGenreByBookId(long bookId) throws DAOException;

    List<Genre> getGenresByIdBook(Long bookId) throws DAOException;

    List<Genre> getGenres() throws DAOException;

    int getCount() throws DAOException;

}
