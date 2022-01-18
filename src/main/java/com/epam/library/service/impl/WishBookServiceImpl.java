package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.WishBookDao;
import com.epam.library.entity.WishBook;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import com.epam.library.service.WishBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class WishBookServiceImpl implements WishBookService {

    private static final Logger logger = LoggerFactory.getLogger(WishBookServiceImpl.class);

    @Override
    public boolean add(String userId, String bookId) throws ServiceException {
        try {
            WishBookDao wishBookDao = DaoFactory.getInstance().getWishBookDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (userId != null && bookId != null) {
                if (validator.isNumber(userId) && validator.isNumber(bookId)) {
                    Optional<WishBook> optionalWishBook = wishBookDao
                            .getWishBookByBookAndByUser(Long.parseLong(bookId.trim()), Long.parseLong(userId.trim()));
                    if (optionalWishBook.isEmpty()) {
                        WishBook wishBook = new WishBook();
                        wishBook.setUserId(Long.parseLong(userId.trim()));
                        wishBook.setBookId(Long.parseLong(bookId.trim()));
                        wishBookDao.create(wishBook);
                        return true;
                    }
                } else {
                    throw new ServiceException("Invalid ID values.");
                }
            } else {
                throw new ServiceException("The user ID or the book ID value is empty.");
            }
        } catch (DaoException e) {
            logger.error("Error adding book to favorites.");
            throw new ServiceException("Error adding book to favorites.", e);
        }
        return false;
    }

    @Override
    public boolean delete(String wishBookId) throws ServiceException {
        try {
            WishBookDao wishBookDao = DaoFactory.getInstance().getWishBookDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (wishBookId != null) {
                if (validator.isNumber(wishBookId)) {
                    Optional<WishBook> optionalWishBook = wishBookDao.getWishBookByID(Long.parseLong(wishBookId.trim()));
                    if (optionalWishBook.isPresent()) {
                        int result = wishBookDao.delete(Long.parseLong(wishBookId.trim()));
                        if (result == 1) {
                            return true;
                        }
                    }
                } else {
                    throw new ServiceException("Invalid ID values.");
                }
            } else {
                throw new ServiceException("The wish book ID value is empty.");
            }
        } catch (DaoException e) {
            logger.error("Error deleting book from favorites.");
            throw new ServiceException("Error deleting book from favorites.", e);
        }
        return false;
    }

    @Override
    public long showCountBooks() throws ServiceException {
        try {
            WishBookDao wishBookDao = DaoFactory.getInstance().getWishBookDao();
            return wishBookDao.getCountWishBooks();
        }catch (DaoException e) {
            logger.error("Error in services when getting the number of favorites.");
            throw new ServiceException("Error in services when getting the number of favorites.", e);
        }
    }
}
