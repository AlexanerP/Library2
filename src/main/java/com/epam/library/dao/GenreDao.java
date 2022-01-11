package com.epam.library.dao;

import com.epam.library.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    boolean create(Genre genre) throws DAOException;

    boolean update(Genre genre) throws DAOException;

    Optional<Genre> getGenreById(long genreId) throws DAOException;

    Optional<Genre> getGenreByGenre(String genre) throws DAOException;

    boolean deleteGenreByBookId(long bookId) throws DAOException;

    List<Genre> getGenresByIdBook(long bookId) throws DAOException;

    List<Genre> getGenres() throws DAOException;

    long getCount() throws DAOException;

    long getCountByGenre(String genre) throws DAOException;

}
