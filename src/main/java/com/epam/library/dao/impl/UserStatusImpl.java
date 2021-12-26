package com.epam.library.dao.impl;

import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOHelper;
import com.epam.library.dao.StatusDAO;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.StatusMapper;
import com.epam.library.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserStatusImpl extends DAOHelper implements StatusDAO {

    private final static Logger logger = LoggerFactory.getLogger(UserStatusImpl.class);

    private final static String ADD_STATUS_QUERY = String.format("INSERT INTO %s(%s) VALUES(?);",
            TableName.USER_STATUS, ColumnName.USER_STATUS_STATUS);

    private final static String DELETE_QUERY = String.format("DELETE FROM %s WHERE %s=?", TableName.USER_STATUS,
            ColumnName.USER_STATUS_ID_STATUS);

    private final static String UPDATE_QUERY = String.format("UPDATE %s SET %s=? WHERE %s=?",
            TableName.USER_STATUS, ColumnName.USER_STATUS_STATUS, ColumnName.USER_STATUS_ID_STATUS);

    private final static String GET_STATUS_BY_ID_QUERY = String.format("SELECT * FROM %s WHERE %s=?;",
            TableName.USER_STATUS, ColumnName.USER_STATUS_ID_STATUS);

    private final static String GET_ALL_STATUS_QUERY = String.format("SELECT * FROM %s;", TableName.USER_STATUS);

    @Override
    public boolean create(String status)  throws DAOException {
        logger.info("Create status.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, ADD_STATUS_QUERY, status);
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Status doesn't create. Status - {}", status);
            throw new DAOException("Status doesn't create.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int delete(Integer id)  throws DAOException{
        logger.info("Delete status.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, DELETE_QUERY, id);
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Status doesn't delete. Id - {}", id);
            throw new DAOException("Status doesn't delete.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int update(Status status)  throws DAOException{
        logger.info("Update status.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_QUERY, status.getValue(), status.getStatusId());
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Status doesn't update. Status - {}", status.toString());
            throw new DAOException("Status doesn't update.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Status> getStatus(Integer id)  throws DAOException{
        logger.info("Getting status.");
        PreparedStatement prStatement = null;
        StatusMapper mapper = new StatusMapper();
        ResultSet resultSet = null;
        List<Status> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_STATUS_BY_ID_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("Status of user received for verification.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                logger.info("Find more 1 status.");
                throw new DAOException("Find more 1 status of user.");
            }
         } catch (SQLException sqlE) {
            logger.error("Statuses not received.");
            throw new DAOException("Status by id not received.", sqlE);

        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<Status> getStatuses()  throws DAOException{
        logger.info("Getting all statuses.");
        PreparedStatement prStatement = null;
        StatusMapper mapper = new StatusMapper();
        ResultSet resultSet = null;
        List<Status> statuses = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = connection.prepareStatement(GET_ALL_STATUS_QUERY);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                statuses.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Statuses not received.");
            throw new DAOException("Statuses not received.", sqlE);

        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return statuses;
    }
}
