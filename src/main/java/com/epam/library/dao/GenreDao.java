package com.epam.library.dao;

import com.epam.library.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    boolean create(Genre genre) throws DaoException;

    boolean update(Genre genre) throws DaoException;

    Optional<Genre> getGenreById(long genreId) throws DaoException;

    Optional<Genre> getGenreByGenre(String genre) throws DaoException;

    boolean deleteGenreByBookId(long bookId) throws DaoException;

    List<Genre> getGenresByIdBook(long bookId) throws DaoException;

    List<Genre> getGenres() throws DaoException;

    long getCount() throws DaoException;

    long getCountByGenre(String genre) throws DaoException;

}
