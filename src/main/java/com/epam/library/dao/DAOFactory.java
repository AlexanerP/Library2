package com.epam.library.dao;

import com.epam.library.dao.test_impl.*;

public class DAOFactory {

    private static DAOFactory instance = new DAOFactory();

    private BookDtoDao bookDtoDao = new BookDtoDaoImpl();
    public BookDao bookDao = new BookDaoImpl();
    private LibraryDAO libraryDAO = new LibraryDaoImpl();
    private UserDAO userDAO = new UserDaoImpl();

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
}
