package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.GenreDao;
import com.epam.library.entity.Genre;
import com.epam.library.service.GenreService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Override
    public List<Genre> getGenres() throws ServiceException {
        try {
            GenreDao genreDao = DaoFactory.getInstance().getGenreDAO();
            return genreDao.getGenres();
        } catch (DaoException e) {
            logger.error("Error getting genres.");
            throw new ServiceException("Error getting genres.", e);
        }
    }

    @Override
    public boolean create(String category) throws ServiceException {
        try {
            GenreDao genreDao = DaoFactory.getInstance().getGenreDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (category != null) {
                if (validator.isLength(category)) {
                    Genre genre = new Genre();
                    genre.setCategory(category);
                    return genreDao.create(genre);
                } else {
                    throw new ServiceException("Error in services the category value is too much higher " +
                            "than the specified one");
                }
            } else {
                throw new ServiceException("Services error category value is empty.");
            }
        } catch (DaoException e) {
            logger.error("Error while creating category.");
            throw new ServiceException("Error while creating category.", e);
        }
    }

    @Override
    public int update(String genreId, String category) throws ServiceException {
        try {
            GenreDao genreDao = DaoFactory.getInstance().getGenreDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(genreId)) {
                if (validator.isLength(category)) {
                    Optional<Genre> optionalGenre = genreDao.getGenreByGenre(category);
                    if (optionalGenre.isEmpty()) {
                        Genre genre = new Genre();
                        genre.setGenreId(Long.parseLong(genreId.trim()));
                        genre.setCategory(category);
                        genreDao.update(genre);
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    return 3;
                }
            } else {
                throw new ServiceException("Genre ID value is not a number.");
            }
        } catch (DaoException e) {
            logger.error("Error updating genre data.");
            throw new ServiceException("Error updating genre data.", e);
        }
    }

    @Override
    public long getCountGenres() throws ServiceException {
        try {
            GenreDao genreDao = DaoFactory.getInstance().getGenreDAO();
            return genreDao.getCount();
        } catch (DaoException e) {
            logger.error("Error in services when getting the number of genres.");
            throw new ServiceException("Error in services when getting the number of genres.", e);
        }
    }

    @Override
    public long getCountBooksByGenres(String category) throws ServiceException {
        try {
            GenreDao genreDao = DaoFactory.getInstance().getGenreDAO();
            return genreDao.getCountByGenre(category);
        } catch (DaoException e) {
            logger.error("Error in services when getting the number of genres by category.");
            throw new ServiceException("Error in services when getting the number of genres by category.", e);
        }
    }

    @Override
    public Optional<Genre> showGenreByCategory(String category) throws ServiceException {
        try {
            GenreDao genreDao = DaoFactory.getInstance().getGenreDAO();
            return genreDao.getGenreByGenre(category);
        } catch (DaoException e) {
            logger.error("Error in services when getting the genre by category.");
            throw new ServiceException("Error in services when getting the genre by category.", e);
        }
    }

    @Override
    public Optional<Genre> showGenreById(String genreId) throws ServiceException {
        try {
            GenreDao genreDao = DaoFactory.getInstance().getGenreDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(genreId)) {
                return genreDao.getGenreById(Long.parseLong(genreId.trim()));
            } else {
                throw new ServiceException("Genre ID value is not a number.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when getting the number of genres by ID.");
            throw new ServiceException("Error in services when getting the number of genres by ID.", e);
        }
    }
}
