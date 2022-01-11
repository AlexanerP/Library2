package com.epam.library.service.impl;

import com.epam.library.dao.AuthorDao;
import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOFactory;
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
            AuthorDao authorDAO = DAOFactory.getInstance().getAuthorDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (name != null) {
                if (validator.isLength(name)) {
                    Author author = new Author();
                    author.setName(name);
                    return authorDAO.create(author);
                } else {
                    throw new ServiceException("Author name value is longer than specified.");
                }
            } else {
                throw new ServiceException("The author value is empty.");
            }
        }catch (DAOException e) {
            logger.error("Error creating author.");
        }
        return false;
    }

    @Override
    public boolean update(String authorId, String name) throws ServiceException {
        try {
            AuthorDao authorDAO = DAOFactory.getInstance().getAuthorDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (authorId != null && name != null) {
                if (validator.isNumber(authorId.trim())) {
                    if (validator.isLength(name)) {
                        Author author = new Author();
                        author.setAuthorId(Long.parseLong(authorId.trim()));
                        author.setName(name);
                        return authorDAO.update(author);
                    } else {
                        throw new ServiceException("Error in services the name value is too much higher " +
                                "than the specified one");
                    }
                } else {
                    throw new ServiceException("Author ID value is not a number.");
                }
            } else {
                throw new ServiceException("The values are empty.");
            }
        } catch (DAOException e) {
            logger.error("Error while updating author details.");
            throw new ServiceException("Error while updating author details.", e);
        }
    }

    @Override
    public int getCountAuthors() throws ServiceException {
        try{
            AuthorDao authorDAO = DAOFactory.getInstance().getAuthorDAO();
            return authorDAO.getCountAuthors();
        }catch (DAOException e) {
            logger.error("Error while getting all authors.");
            throw new ServiceException("Error while getting all authors.", e);
        }
    }

    @Override
    public int getCountBooksByAuthors(String name) throws ServiceException {
        try {
            AuthorDao authorDAO = DAOFactory.getInstance().getAuthorDAO();
            if (name != null) {
                Optional<Author> optionalAuthor = authorDAO.getAuthorByName(name);
                if (optionalAuthor.isPresent()) {
                    return authorDAO.getCountBooksByAuthor(optionalAuthor.get().getName());
                }
            } else {
                throw new ServiceException("Author value is empty.");
            }
        } catch (DAOException e) {
            logger.error("An error occurred while getting the number of author's books.");
            throw new ServiceException("An error occurred while getting the number of author's books.", e);
        }
        return 0;
    }

    @Override
    public Optional<Author> showAuthorById(String authorId) throws ServiceException {
        try{
            AuthorDao authorDAO = DAOFactory.getInstance().getAuthorDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (authorId != null) {
                if (validator.isNumber(authorId)) {
                    return authorDAO.getAuthorById(Long.parseLong(authorId.trim()));
                } else {
                    throw new ServiceException("Author ID value is not a number.");
                }
            } else {
                throw new ServiceException("Author ID is empty.");
            }
        }catch (DAOException e) {
            logger.error("Getting an author by name.");
            throw new ServiceException("Getting an author.", e);
        }
    }

    @Override
    public Optional<Author> showAuthorByName(String name) throws ServiceException {
        try{
            AuthorDao authorDAO = DAOFactory.getInstance().getAuthorDAO();
            return authorDAO.getAuthorByName(name);
        }catch (DAOException e) {
            logger.error("Getting an author by name.");
            throw new ServiceException("Getting an author.", e);
        }
    }

    @Override
    public List<Author> showAllAuthors() throws ServiceException {
        try {
            AuthorDao authorDAO = DAOFactory.getInstance().getAuthorDAO();
            return authorDAO.getAuthors();
        }catch (DAOException e) {
            logger.error("Error getting all authors.");
            throw new ServiceException("Error getting all authors.", e);
        }
    }
}
