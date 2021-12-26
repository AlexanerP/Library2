package com.epam.library.service;

import com.epam.library.entity.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getGenres() throws ServiceException;

    int countGenres() throws ServiceException;
}
