package com.epam.library.service.impl;

import com.epam.library.entity.Genre;
import com.epam.library.service.GenreService;
import com.epam.library.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceImpl implements GenreService {
    @Override
    public List<Genre> getGenres() throws ServiceException {
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Genre genre = new Genre();
            genre.setCategory("genre - " + i);
            genres.add(genre);
        }
        return genres;
    }

    @Override
    public int countGenres() throws ServiceException {
        return 21;
    }
}
