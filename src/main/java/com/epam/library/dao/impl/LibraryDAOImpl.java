package com.epam.library.dao.impl;

import com.epam.library.dao.LibraryDAO;
import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOHelper;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.LibraryMapper;
import com.epam.library.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryDAOImpl extends DAOHelper implements LibraryDAO {

    private static final Logger logger = LoggerFactory.getLogger(LibraryDAOImpl.class);

    private final static String ADD_LIBRARY_QUERY = String.format("INSERT INTO %s(%s, %s) values(?, ?);",
            TableName.LIBRARY, ColumnName.LIBRARY_CITY, ColumnName.LIBRARY_STREET);

    private final static String UPDATE_QUERY = String.format("UPDATE %s SET %s=?, %s=? WHERE %s=?;",
            TableName.LIBRARY, ColumnName.LIBRARY_CITY, ColumnName.LIBRARY_STREET, ColumnName.LIBRARY_ID_LIBRARY);

    private final static String UPDATE_STREET_QUERY = String.format("UPDATE %s SET %s=? WHERE %s=?;", TableName.LIBRARY,
            ColumnName.LIBRARY_STREET, ColumnName.LIBRARY_ID_LIBRARY);

    private final static String GET_CITY_BY_ID_QUERY = String.format("SELECT * FROM %s WHERE %s=?;", TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY);

    private final static String GET_CITY_BY_NAME_QUERY = String.format("SELECT * FROM %s WHERE %s=?;", TableName.LIBRARY,
            ColumnName.LIBRARY_CITY);

    private final static String GET_ALL_CITY_QUERY = String.format("SELECT * FROM %s;", TableName.LIBRARY);

    @Override
    public boolean create(Library library) throws DAOException {
        logger.info("Create library {}", library.getCity());
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, ADD_LIBRARY_QUERY, library.getCity(),
                    library.getStreet());
            prStatement.execute();

            return true;

        } catch (SQLException sqlE) {
            logger.error("Failed to create library. Library - {}.", library.toString());
            throw new DAOException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int update(Library library) throws DAOException {
        logger.info("Start of updating library data.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_QUERY, library.getCity(), library.getStreet(),
                    library.getLibraryId());
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Failed to update library. Library - {}", library.toString());
            throw new DAOException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int updateStreet(Long id, String street) throws DAOException {
        logger.info("Start of updating city address.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_STREET_QUERY, street, id);
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Library address update error. Street - {}, id - {}", street, id);
            for (Throwable e : sqlE) {
                logger.error(e.toString());
            }
            throw new DAOException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Library> getLibraryById(Long id) throws DAOException {
        logger.info("Receiving a city by id.");
        LibraryMapper mapper = new LibraryMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Library> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CITY_BY_ID_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if (entity.size() == 1) {
                logger.info("Library by id received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new UnsupportedOperationException("Find more 1 user.");
            }
        } catch (SQLException sqlE) {
            logger.error("Find more 1 city. Find - {}", entity.toString());
            throw new DAOException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Library> getLibraryByCity(String city) throws DAOException {
        logger.info("Receiving a city by name.");
        LibraryMapper mapper = new LibraryMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Library> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CITY_BY_NAME_QUERY, city);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if (entity.size() == 1) {
                logger.info("Library by name received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new UnsupportedOperationException("Find more 1 user.");
            }
        } catch (SQLException sqlE) {
            logger.error("Find more 1 city. Find - {}", entity.toString());
            throw new DAOException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<Library> getLibraries() throws DAOException {
        logger.info("Getting all cities.");
        List<Library> cities = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = connection.prepareStatement(GET_ALL_CITY_QUERY);
            resultSet = prStatement.executeQuery();
            LibraryMapper mapper = new LibraryMapper();
            while (resultSet.next()) {
                cities.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Cities not received.");
            throw new DAOException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Library list received.");
        return cities;
    }
}
