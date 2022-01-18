package com.epam.library.dao.impl;

import com.epam.library.dao.GenreDao;
import com.epam.library.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenreDaoImplTest {

    private GenreDao genreDAO;

    @BeforeEach
    public void setUp() throws Exception {
        genreDAO = new GenreDaoImpl();
    }

    @Test
    public void create() {
        Genre genre = new Genre("new genre");
        boolean condition = genreDAO.create(genre);
        assertTrue(condition);
    }

    @Test
    public void update() {
        Genre genre = new Genre();
        genre.setCategory("Update");
        genre.setGenreId(2L);
        boolean condition = genreDAO.update(genre);
        assertTrue(condition);
    }

    @Test
    public void getGenres() {
        List<Genre> genres = genreDAO.getGenres();
        for (Genre genre : genres) {
            System.out.println(genre);
        }
        assertFalse(genres.isEmpty());
    }
}