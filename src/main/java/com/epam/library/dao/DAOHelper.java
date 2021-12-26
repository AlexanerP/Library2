package com.epam.library.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAOHelper {

    private final static Logger logger = LoggerFactory.getLogger(DAOHelper.class);

    public PreparedStatement createPreparedStatement(Connection connection, String query, Object... parameters) throws SQLException {
        logger.info("Create statement. Query - {}", query);
        PreparedStatement prStatement = connection.prepareStatement(query);
        for (int i = 1; i <= parameters.length; i++) {
            logger.info("Parameters i - {}, value - {}", i, parameters[i - 1]);
            prStatement.setObject(i, parameters[i - 1]);
        }
        return prStatement;
    }

    public void closePreparedStatement(PreparedStatement statement) {
        logger.info("Start to close statement.");
        try {
            if (!(statement == null)) {
                statement.close();
            }
        } catch (SQLException sqlE) {
            logger.error("An error occured while closing PreparedStatement.");
            for (Throwable e : sqlE) {
                logger.error(e.toString());
            }
        }
        logger.info("Finish to close statement.");
    }

    public void closeResultSet(ResultSet resultSet) {
        logger.info("Start to close resultSet.");
        try {
            if (!(resultSet == null)) {
                resultSet.close();
            }
        } catch (SQLException sqlE) {
            logger.error("An error occured while closing ResultSet.");
            for (Throwable e : sqlE) {
                logger.error(e.toString());
            }
        }
        logger.info("Finish to close resultSet.");
    }
}
