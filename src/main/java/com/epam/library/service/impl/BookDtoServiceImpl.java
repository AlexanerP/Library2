package com.epam.library.service.impl;

import com.epam.library.dao.BookDtoDao;
import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOFactory;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.Author;
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

public class BookDtoServiceImpl implements BookDtoService {

    private static final Logger logger = LoggerFactory.getLogger(BookDtoServiceImpl.class);

    @Override
    public boolean create(String title, String isbn, String publisher, String year, String count,
                          String city, String shelf, String author, String genre,
                          String description) throws ServiceException {
        try {
            ServiceValidator validator = new ServiceValidator();
            BookDtoDao bookDtoDao = DAOFactory.getInstance().getBookDtoDao();
            if (validator.isLength(title) && validator.isLength(isbn) && validator.isLength(publisher) &&
                validator.isLength(year) && validator.isLength(city) && validator.isLength(shelf) &&
                validator.isNumber(count)) {
                BookDto book = new BookDto();
                book.setTitle(title);
                book.setIsbn(isbn);
                book.setPublisher(publisher);
                book.setYear(year);
                book.setCityLibrary(city);
                book.setShelf(shelf);
                book.setQuantity(Integer.parseInt(count));
                book.setDescription(description);

                List<String> authorLine = Arrays.asList(author.split("/"));
                List<String> genreLine = Arrays.asList(genre.split("/"));

                List<Author> authors = new ArrayList<>();
                for (String setAuthor : authorLine) {
                    authors.add(new Author(setAuthor.trim()));
                }
                book.setAuthors(authors);

                List<Genre> genres = new ArrayList<>();
                for (String setGenre : genreLine) {
                    genres.add(new Genre(setGenre.trim()));
                }
                book.setGenres(genres);
                return bookDtoDao.create(book);
            } else {
                return false;
            }
        } catch (DAOException e) {
            logger.error("An error occurred while preparing the book for saving to the database");
            throw new ServiceException("An error occurred while preparing the book for saving to the database", e);
        }
    }

    @Override
    public boolean update(String bookId, BookDto bookDto, String author, String genre, String quantity) throws ServiceException {
        try {
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            BookDtoDao bookDtoDao = DAOFactory.getInstance().getBookDtoDao();
            BookDto newBookDto = new BookDto();
            if (validator.isLengthForUpdate(bookDto.getTitle()) && validator.isLengthForUpdate(bookDto.getIsbn()) &&
                    validator.isLengthForUpdate(bookDto.getPublisher()) && validator.isLengthForUpdate(bookDto.getYear()) &&
                    validator.isLengthForUpdate(bookDto.getShelf())) {

                Optional<BookDto> optionalBookDto = bookDtoDao.getBookById(Long.parseLong(bookId));

                newBookDto.setBookDtoId(optionalBookDto.get().getBookDtoId());
                newBookDto.setTitle(bookDto.getTitle() != "" ? bookDto.getTitle() : optionalBookDto.get().getTitle());
                newBookDto.setIsbn(bookDto.getIsbn() != "" ? bookDto.getIsbn() : optionalBookDto.get().getIsbn());
                newBookDto.setPublisher(bookDto.getPublisher() != "" ? bookDto.getPublisher() :
                        optionalBookDto.get().getPublisher());
                newBookDto.setYear(bookDto.getYear() != "" ? bookDto.getYear() : optionalBookDto.get().getYear());
                newBookDto.setShelf(bookDto.getShelf() != "" ? bookDto.getShelf() : optionalBookDto.get().getShelf());
                newBookDto.setCityLibrary(bookDto.getCityLibrary() != "" ? bookDto.getCityLibrary() :
                        optionalBookDto.get().getCityLibrary());
                newBookDto.setDescription(bookDto.getDescription() != "" ? bookDto.getDescription() :
                        optionalBookDto.get().getDescription());
                newBookDto.setAdded(optionalBookDto.get().getAdded());
                newBookDto.setBorrow(optionalBookDto.get().getBorrow());
                if (validator.isNumber(bookId) &&  validator.isNumber(quantity)) {
                    newBookDto.setQuantity(quantity != "" ? Integer.parseInt(quantity) :
                            optionalBookDto.get().getQuantity());
                }

                List<String> authorLine = Arrays.asList(author.split("/"));
                List<String> genreLine = Arrays.asList(genre.split("/"));

                List<Author> authors = new ArrayList<>();
                for (String setAuthor : authorLine) {
                    authors.add(new Author(setAuthor.trim()));
                }
                newBookDto.setAuthors(authors);
                List<Genre> genres = new ArrayList<>();
                for (String setGenre : genreLine) {
                    genres.add(new Genre(setGenre.trim()));
                }
                newBookDto.setGenres(genres);
                bookDtoDao.update(newBookDto);
            }
        } catch (DAOException e) {
            logger.error("Error in services while preparing a book update.", e);
        }
        return false;
    }

    @Override
    public List<BookDto> showCatalog() throws ServiceException {
        try{
            logger.error("Request for all books.");
            List<BookDto> booksDto;
            BookDtoDao bookDtoDao = DAOFactory.getInstance().getBookDtoDao();
            booksDto = bookDtoDao.getBooks();
            return booksDto;
        } catch (DAOException e) {
            logger.error("All books not received.");
            throw new ServiceException("All books not received.", e);
        }
    }

    @Override
    public List<BookDto> showBookByParameter(String title, String isbn, String genre, String author) throws ServiceException {
        logger.info("Retrieving books by parameters");
        List<BookDto> booksDto = new ArrayList<>();
        BookDtoDao bookDtoDao = DAOFactory.getInstance().getBookDtoDao();
        ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
        try {
            if (isbn != null && isbn != "" && validator.isLength(isbn)) {
                booksDto = bookDtoDao.getBookByIsbn(isbn);
            }
        }catch (DAOException e) {
            logger.error("Error in services when retrieving books via ISBN.");
            throw  new ServiceException("Error in services when retrieving books via ISBN.", e);
        }
        try {
            if (title != null && title != "" && validator.isLength(title)) {
                booksDto.addAll(bookDtoDao.getBooksByTitle(title));
            }
        } catch (DAOException e) {
            logger.error("Error in services when retrieving books by title.");
            throw new ServiceException("Error in services when retrieving books by title.", e);
        }
        try{
            if (genre != null && genre != "" && validator.isLength(genre)) {
                booksDto.addAll(bookDtoDao.getBooksByGenre(genre));
            }
        }catch (DAOException e) {
            logger.error("Error in services when retrieving books by genre.");
            throw new ServiceException("Error in services when retrieving books by genre.", e);
        }
        try {
            if (author != null && author != "" && validator.isLength(author)) {
                booksDto.addAll(bookDtoDao.getBooksByAuthor(author));
            }
        }catch (DAOException e) {
            logger.error("Error in services when retrieving books by author.");
            throw new ServiceException("Error in services when retrieving books by author.", e);
        }
        return booksDto;
    }

    @Override
    public Optional<BookDto> showBookById(String bookId) throws ServiceException {
        try {
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            BookDtoDao bookDtoDao = DAOFactory.getInstance().getBookDtoDao();
            System.out.println("showBookById - " + validator.isNumber(bookId));
            if (validator.isNumber(bookId)) {
                return bookDtoDao.getBookById(Long.parseLong(bookId));
            }
        } catch (DAOException e) {
            logger.error("Error in services when retrieving a book by ID. ID - {}", bookId);
            throw new ServiceException("Error in services when retrieving a book by ID.", e);
        }
        return Optional.empty();
    }

    @Override
    public List<BookDto> showBookByCity(String city) throws ServiceException {
        try {
            BookDtoDao bookDtoDao = DAOFactory.getInstance().getBookDtoDao();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            if (city != null) {
                Optional<Library> optionalLibrary = libraryService.showByCity(city);
                if (optionalLibrary.isPresent()) {
                    return bookDtoDao.getBooksByCity(city);
                } else {
                    throw new ServiceException("A library with such a city does not exist.");
                }
            }else {
                throw new ServiceException("The city value is empty");
            }
        } catch (DAOException e) {
            logger.error("Error retrieving books from the library.");
            throw new ServiceException("Error retrieving books from the library.", e);
        }
    }

    @Override
    public List<BookDto> showByPage(int page, int limit) throws ServiceException {
        try {
            BookDtoDao bookDtoDao = DAOFactory.getInstance().getBookDtoDao();
            return bookDtoDao.getBooksByPage(limit, page);
        } catch (DAOException e) {
            logger.error("Error in services when retrieving books by page.");
            throw new ServiceException("Error in services when retrieving books by page.", e);
        }
    }
}
