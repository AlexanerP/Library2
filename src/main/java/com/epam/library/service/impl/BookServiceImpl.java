package com.epam.library.service.impl;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOFactory;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.entity.Library;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public long getCountBooks() throws ServiceException {
        try {
            BookDao bookDao = DAOFactory.getInstance().getBookDao();
            return bookDao.getCountBooks();
        } catch (DAOException e) {
            logger.error("Error in services when getting the number of books.");
            throw new ServiceException("Error in services when getting the number of books.", e);
        }
    }

    @Override
    public boolean update(String bookId, Book book, String quantity,  String cityLibrary) throws ServiceException {
        try {
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            BookDao bookDao = DAOFactory.getInstance().getBookDao();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            Book newBook = new Book();
            if (validator.isLength(book.getTitle()) && validator.isLength(book.getIsbn()) &&
                    validator.isLength(book.getPublisher()) && validator.isLength(book.getYear()) &&
                    validator.isLength(book.getShelf())){

                Optional<Book> optionalBook = bookDao.getBookById(Long.parseLong(bookId));
                Optional<Library> optionalLibrary = libraryService.showByCity(cityLibrary);
                newBook.setBookId(optionalBook.get().getBookId());
                newBook.setTitle(book.getTitle() != "" ? book.getTitle() : optionalBook.get().getTitle());
                newBook.setIsbn(book.getIsbn() != "" ? book.getIsbn() : optionalBook.get().getIsbn());
                newBook.setPublisher(book.getPublisher() != "" ? book.getPublisher() :
                        optionalBook.get().getPublisher());
                newBook.setYear(book.getYear() != "" ? book.getYear() : optionalBook.get().getYear());
                newBook.setShelf(book.getShelf() != "" ? book.getShelf() : optionalBook.get().getShelf());
                newBook.setLibraryId(cityLibrary != "" ? optionalLibrary.get().getLibraryId() :
                        optionalBook.get().getLibraryId());
                newBook.setDescription(book.getDescription() != "" ? book.getDescription() :
                        optionalBook.get().getDescription());
                if (validator.isNumber(quantity)) {
                    newBook.setQuantity(quantity != "" ? Integer.parseInt(quantity) :
                            optionalBook.get().getQuantity());
                }
                newBook.setAdded(optionalBook.get().getAdded());
                newBook.setBorrow(optionalBook.get().getBorrow());
System.out.println(optionalBook);
                bookDao.update(newBook);

            }
            return true;
        }catch (DAOException e) {
            logger.error("An error in services when preparing to update a book without authors and genres.");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateBorrow(String bookId) throws ServiceException {
        try {
            System.out.println("updateBorrow");
            BookDao bookDao = DAOFactory.getInstance().getBookDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(bookId)) {
                Optional<Book> optionalBook = bookDao.getBookById(Long.parseLong(bookId));
                if (optionalBook.get().getBorrow() < optionalBook.get().getQuantity()) {
                    bookDao.updateBorrow(Long.parseLong(bookId), optionalBook.get().getBorrow() + 1);
                } else {
                    return false;
                }
            }
            return true;
        } catch (DAOException e) {
            logger.error("The column 'On issue' has not been updated.");
            throw new ServiceException("The column 'On issue' has not been updated.", e);
        }
    }
}
