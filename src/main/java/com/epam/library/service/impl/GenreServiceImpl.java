package com.epam.library.service.impl;

import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOFactory;
import com.epam.library.dao.GenreDAO;
import com.epam.library.entity.Genre;
import com.epam.library.service.GenreService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Override
    public List<Genre> getGenres() throws ServiceException {
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            return genreDAO.getGenres();
        } catch (DAOException e) {
            logger.error("Error getting genres.");
            throw new ServiceException("Error getting genres.", e);
        }
    }

    @Override
    public boolean create(String category) throws ServiceException {
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (category != null) {
                if (validator.isLength(category)) {
                    Genre genre = new Genre();
                    genre.setCategory(category);
                    return genreDAO.create(genre);
                } else {
                    throw new ServiceException("Error in services the category value is too much higher " +
                            "than the specified one");
                }
            } else {
                throw new ServiceException("Services error category value is empty.");
            }
        } catch (DAOException e) {
            logger.error("Error while creating category.");
            throw new ServiceException("Error while creating category.", e);
        }
    }

    @Override
    public boolean update(String genreId, String category) throws ServiceException {
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (genreId != null && category != null) {
                if (validator.isNumber(genreId)) {
                    if (validator.isLength(category)) {
                        Genre genre = new Genre();
                        genre.setGenreId(Long.parseLong(genreId.trim()));
                        genre.setCategory(category);
                        return genreDAO.update(genre);
                    } else {
                        throw new ServiceException("Error in services the category value is too much higher " +
                                "than the specified one");
                    }
                } else {
                    throw new ServiceException("Genre ID value is not a number.");
                }
            } else {
                throw new ServiceException("Services error values are empty.");
            }
        } catch (DAOException e) {
            logger.error("Error updating genre data.");
            throw new ServiceException("Error updating genre data.", e);
        }
    }

    @Override
    public long getCountGenres() throws ServiceException {
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            return genreDAO.getCount();
        } catch (DAOException e) {
            logger.error("Error in services when getting the number of genres.");
            throw new ServiceException("Error in services when getting the number of genres.", e);
        }
    }

    @Override
    public long getCountBooksByGenres(String category) throws ServiceException {
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            if (category != null) {
                return genreDAO.getCountByGenre(category);
            } else {
                throw new ServiceException("Services error the category value is empty.");
            }
        } catch (DAOException e) {
            logger.error("Error in services when getting the number of genres by category.");
            throw new ServiceException("Error in services when getting the number of genres by category.", e);
        }
    }

    @Override
    public Optional<Genre> showGenreByCategory(String category) throws ServiceException {
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            if (category != null) {
                return genreDAO.getGenreByGenre(category);
            } else {
                throw new ServiceException("Services error the category value is empty.");
            }
        } catch (DAOException e) {
            logger.error("Error in services when getting the genre by category.");
            throw new ServiceException("Error in services when getting the genre by category.", e);
        }
    }

    @Override
    public Optional<Genre> showGenreById(String genreId) throws ServiceException {
        try {
            GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (genreId != null) {
                if (validator.isNumber(genreId)) {
                    return genreDAO.getGenreById(Long.parseLong(genreId.trim()));
                } else {
                    throw new ServiceException("Genre ID value is not a number.");
                }
            } else {
                throw new ServiceException("Services error the ID value is empty.");
            }
        } catch (DAOException e) {
            logger.error("Error in services when getting the number of genres by ID.");
            throw new ServiceException("Error in services when getting the number of genres by ID.", e);
        }
    }
}
