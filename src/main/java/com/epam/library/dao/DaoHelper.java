package com.epam.library.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoHelper {

    private final static Logger logger = LoggerFactory.getLogger(DaoHelper.class);

    public PreparedStatement createPreparedStatement(Connection connection, String query, Object... parameters) throws DaoException {
        try {
            logger.info("Create statement. Query - {}", query);
            PreparedStatement prStatement = connection.prepareStatement(query);
            for (int i = 1; i <= parameters.length; i++) {
                logger.info("Parameters i - {}, value - {}", i, parameters[i - 1]);
                prStatement.setObject(i, parameters[i - 1]);
            }
            return prStatement;
        }catch (SQLException sqlException) {
            logger.error("Error creating PreparedStatement.");
            throw new DaoException("Error creating PreparedStatement.", sqlException);
        }
    }

    public void closePreparedStatement(PreparedStatement statement) throws DaoException {
        logger.info("Start to close statement.");
        try {
            if (!(statement == null)) {
                statement.close();
            }
        } catch (SQLException sqlE) {
            logger.error("An error occured while closing PreparedStatement.");
            throw new DaoException("An error occured while closing PreparedStatement.", sqlE);
        }
        logger.info("Finish to close statement.");
    }

    public void closeResultSet(ResultSet resultSet) throws DaoException {
        logger.info("Start to close resultSet.");
        try {
            if (!(resultSet == null)) {
                resultSet.close();
            }
        } catch (SQLException sqlE) {
            logger.error("An error occured while closing ResultSet.");
            throw new DaoException("An error occured while closing ResultSet.", sqlE);
        }
        logger.info("Finish to close resultSet.");
    }
}
