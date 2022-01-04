package com.epam.library.dao;

import com.epam.library.dao.impl.AuthorDAOImpl;
import com.epam.library.dao.impl.GenreDAOImpl;

import com.epam.library.dao.test_impl.*;

public class DAOFactory {

    private static DAOFactory instance = new DAOFactory();

    private BookDtoDao bookDtoDao = new BookDtoDaoImpl();
    public BookDao bookDao = new BookDaoImpl();
    private LibraryDAO libraryDAO = new LibraryDaoImpl();
    private UserDAO userDAO = new UserDaoImpl();
    private WishBookDao wishBookDao = new WishBookDaoImpl();
    private WishBookDtoDao wishBookDtoDao = new WishBookDtoDaoImpl();
    private OrderBookDtoDao orderBookDtoDao = new OrderBookDtoDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private AuthorDAO authorDAO = new AuthorDAOImpl();
    private GenreDAO genreDAO = new GenreDAOImpl();
    private LoanCardDtoDao loanCardDtoDao = new LoanCardDtoDaoImpl();
    private LoanCardDao loanCardDao = new LoanCardDaoImpl();

    public static DAOFactory getInstance() {
        return instance;
    }


    public BookDtoDao getBookDtoDao() {
        return bookDtoDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public LibraryDAO getLibraryDAO() {
        return libraryDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public WishBookDao getWishBookDao() {
        return wishBookDao;
    }

    public WishBookDtoDao getWishBookDtoDao() {
        return wishBookDtoDao;
    }

    public OrderBookDtoDao getOrderBookDtoDao() {
        return orderBookDtoDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }

    public GenreDAO getGenreDAO() {
        return genreDAO;
    }

    public LoanCardDtoDao getLoanCardDtoDao() {
        return loanCardDtoDao;
    }

    public LoanCardDao getLoanCardDao() {
        return loanCardDao;
    }
}
