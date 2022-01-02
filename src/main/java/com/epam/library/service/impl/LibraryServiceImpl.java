package com.epam.library.service.impl;

import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOFactory;
import com.epam.library.dao.LibraryDAO;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @Override
    public boolean create(String city, String street) throws ServiceException {
        try {
            LibraryDAO libraryDAO = DAOFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isLength(city) && validator.isLength(street)) {
                Library library = new Library();
                library.setStreet(street);
                library.setCity(city);
                library.setStatus(LibraryStatus.OPENED);
                return libraryDAO.create(library);
            } else {
                throw new ServiceException("Services error while updating the library. The length of the word " +
                        "is longer.");
            }
        }catch (DAOException e) {
            logger.error("Error while creating a library.");
            throw new ServiceException("Error while creating a library.", e);
        }
    }

    @Override
    public List<Library> showAll() throws ServiceException {
        try {
            LibraryDAO libraryDAO = DAOFactory.getInstance().getLibraryDAO();
            return libraryDAO.getLibraries();
        }catch (DAOException e) {
            logger.error("Error getting all libraries.");
            throw new ServiceException("Error getting all libraries.", e);
        }
    }

    @Override
    public boolean updateStatus(String libraryId, String status) throws ServiceException {
        try {
            LibraryDAO libraryDAO = DAOFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(libraryId.trim())) {
                if (status.equalsIgnoreCase(LibraryStatus.OPENED.name()) ||
                        status.equalsIgnoreCase(LibraryStatus.CLOSED.name())) {
                    Optional<Library> optionalLibrary = libraryDAO.getLibraryById(Long.parseLong(libraryId.trim()));
                    if (optionalLibrary.isPresent()) {
                        Library library = optionalLibrary.get();
                        library.setStatus(LibraryStatus.valueOf(status.toUpperCase()));
                        libraryDAO.update(library);
                        return true;
                    }

                } else {
                    throw new ServiceException("An error occurred in services while updating the status in the " +
                            "library. Incorrect status");
                }
            } else {
                throw new ServiceException("Trying to get a library by ID using a text string that does not " +
                        "have a digit");
            }
        }catch (DAOException e) {
            logger.error("Services error when updating the status in the library.");
            throw new ServiceException("Services error when updating the status in the library.", e);
        }
        return false;
    }

    @Override
    public boolean update(String libraryId, String city, String street) throws ServiceException {
        try {
            LibraryDAO libraryDAO = DAOFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(libraryId.trim())) {
                Optional<Library> optionalLibrary = libraryDAO.getLibraryById(Long.parseLong(libraryId.trim()));
                if (validator.isLength(city) && validator.isLength(street)) {
                    if (optionalLibrary.isPresent()) {
                        Library library = new Library();
                        library.setLibraryId(Integer.parseInt(libraryId.trim()));
                        library.setCity(city);
                        library.setStreet(street);
                        library.setStatus(optionalLibrary.get().getStatus());
                        libraryDAO.update(library);
                        return true;
                    }
                }else {
                    throw new ServiceException("Services error while updating the library. The length of the word " +
                            "is longer.");
                }
            } else {
                throw new ServiceException("Trying to get a library by ID using a text string that does not " +
                        "have a digit");
            }
        }catch (DAOException e) {
            logger.error("Services error when updating the library.");
            throw new ServiceException("Services error when updating the library.", e);
        }
        return false;
    }

    @Override
    public Optional<Library> showById(String libraryId) throws ServiceException {
        try {
            LibraryDAO libraryDAO = DAOFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            Optional<Library> libraryOptional = Optional.empty();
            if(validator.isNumber(libraryId.trim())) {
                libraryOptional = libraryDAO.getLibraryById(Long.parseLong(libraryId.trim()));
            } else {
                throw new ServiceException("Trying to get a library by ID using a text string that does not " +
                        "have a digit");
            }
            return libraryOptional;
        } catch (DAOException e) {
            logger.error("Error in services when retrieving a library by ID.");
            throw new ServiceException("Error in services when retrieving a library by ID.", e);
        }
    }

    @Override
    public Optional<Library> showByCity(String city) throws ServiceException {
        try {
            LibraryDAO libraryDAO = DAOFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            Optional<Library> optionalLibrary = Optional.empty();
            if (validator.isLength(city)) {
                optionalLibrary = libraryDAO.getLibraryByCity(city);
            }
            return optionalLibrary;
        }catch (DAOException e) {
            logger.error("Error in services when getting a library around the city.");
            throw new ServiceException("Error in services when getting a library around the city.", e);
        }
    }

    @Override
    public List<Library> showByStatus(String status) throws ServiceException {
        try {
            LibraryDAO libraryDAO = DAOFactory.getInstance().getLibraryDAO();
            if (status.equalsIgnoreCase(LibraryStatus.OPENED.name()) ||
                    status.equalsIgnoreCase(LibraryStatus.CLOSED.name())) {
                return  libraryDAO.getLibrariesByStatus(LibraryStatus.valueOf(status.toUpperCase()));
            }else {
                throw new ServiceException("Unknown library status");
            }

        } catch (DAOException e) {
            logger.error("Error in services when retrieving a libraries by status.");
            throw new ServiceException("Error in services when retrieving a libraries by status.", e);
        }
    }
}
