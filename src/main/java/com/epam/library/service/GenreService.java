package com.epam.library.service;

import com.epam.library.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> getGenres() throws ServiceException;

    boolean create(String category) throws ServiceException;

    boolean update(String genreId, String category) throws ServiceException;

    long getCountGenres() throws ServiceException;

    long getCountBooksByGenres(String category) throws ServiceException;

    Optional<Genre> showGenreByCategory(String category) throws ServiceException;

    Optional<Genre> showGenreById(String genreId) throws ServiceException;
}
