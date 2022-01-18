package com.epam.library.service.impl;

import com.epam.library.dao.AuthorDao;
import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.entity.Author;
import com.epam.library.service.AuthorService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Override
    public boolean create(String name) throws ServiceException {
        try {
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isLength(name)) {
                Author author = new Author();
                author.setName(name);
                return authorDao.create(author);
            } else {
                throw new ServiceException("Author name value is longer than specified.");
            }
        }catch (DaoException e) {
            logger.error("Error creating author.");
        }
        return false;
    }

    @Override
    public int update(String authorId, String name) throws ServiceException {
        try {
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
                if (validator.isNumber(authorId.trim())) {
                    if (validator.isLength(name)) {
                        Optional<Author> optionalAuthor = authorDao.getAuthorByName(name);
                        if (optionalAuthor.isEmpty()) {
                            Author author = new Author();
                            author.setAuthorId(Long.parseLong(authorId.trim()));
                            author.setName(name);
                            authorDao.update(author);
                            return 1;
                        } else {
                            return 2;
                        }
                    } else {
                        return 3;
                    }
                } else {
                    throw new ServiceException("Author ID value is not a number.");
                }
        } catch (DaoException e) {
            logger.error("Error while updating author details.");
            throw new ServiceException("Error while updating author details.", e);
        }
    }

    @Override
    public int getCountAuthors() throws ServiceException {
        try{
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDAO();
            return authorDao.getCountAuthors();
        }catch (DaoException e) {
            logger.error("Error while getting all authors.");
            throw new ServiceException("Error while getting all authors.", e);
        }
    }

    @Override
    public int getCountBooksByAuthors(String name) throws ServiceException {
        try {
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDAO();
            Optional<Author> optionalAuthor = authorDao.getAuthorByName(name);
            if (optionalAuthor.isPresent()) {
                return authorDao.getCountBooksByAuthor(optionalAuthor.get().getName());
            }
        } catch (DaoException e) {
            logger.error("An error occurred while getting the number of author's books.");
            throw new ServiceException("An error occurred while getting the number of author's books.", e);
        }
        return 0;
    }

    @Override
    public Optional<Author> showAuthorById(String authorId) throws ServiceException {
        try{
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
                if (validator.isNumber(authorId)) {
                    return authorDao.getAuthorById(Long.parseLong(authorId.trim()));
                } else {
                    throw new ServiceException("Author ID value is not a number.");
                }
        }catch (DaoException e) {
            logger.error("Getting an author by name.");
            throw new ServiceException("Getting an author.", e);
        }
    }

    @Override
    public Optional<Author> showAuthorByName(String name) throws ServiceException {
        try{
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDAO();
            return authorDao.getAuthorByName(name);
        }catch (DaoException e) {
            logger.error("Getting an author by name.");
            throw new ServiceException("Getting an author.", e);
        }
    }

    @Override
    public List<Author> showAllAuthors() throws ServiceException {
        try {
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDAO();
            return authorDao.getAuthors();
        }catch (DaoException e) {
            logger.error("Error getting all authors.");
            throw new ServiceException("Error getting all authors.", e);
        }
    }
}
