package com.epam.library.dao;

import com.epam.library.dao.connection.ProxyConnection;
import com.epam.library.dao.impl.UserDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DAOFactory {

    private static DAOFactory instance = new DAOFactory();
    private static UserDAO userDAO= new UserDAOImpl();

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
