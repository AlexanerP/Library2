package com.epam.library.dao.impl;

import com.epam.library.dao.*;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.OrderDtoMapper;
import com.epam.library.entity.*;
import com.epam.library.entity.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDtoDAOImpl extends DAOHelper implements OrderBookDtoDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDtoDAOImpl.class);

    private final static String GET_ORDER_BY_ID_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s.%s=?",
            TableName.ORDER, TableName.USER, TableName.ORDER, ColumnName.ORDER_ID_USER, TableName.USER,
            ColumnName.USER_ID_USERS, TableName.LIBRARY, TableName.ORDER, ColumnName.ORDER_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.BOOK, ColumnName.BOOK_ID_BOOK,
            TableName.ORDER, ColumnName.ORDER_ID_BOOK, TableName.ORDER_STATUS, TableName.ORDER,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER,
            ColumnName.ORDER_ID_REQUEST);

    private final static String GET_ORDER_BY_USER_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s.%s=? group by %s.%s",
            TableName.ORDER, TableName.USER, TableName.ORDER, ColumnName.ORDER_ID_USER, TableName.USER,
            ColumnName.USER_ID_USERS, TableName.LIBRARY, TableName.ORDER, ColumnName.ORDER_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.BOOK, ColumnName.BOOK_ID_BOOK,
            TableName.ORDER, ColumnName.ORDER_ID_BOOK, TableName.ORDER_STATUS, TableName.ORDER,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER,
            ColumnName.ORDER_ID_USER, TableName.ORDER, ColumnName.ORDER_ID_REQUEST);

    private final static String GET_ORDER_BY_STATUS_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s.%s=? group by %s.%s",
            TableName.ORDER, TableName.USER, TableName.ORDER, ColumnName.ORDER_ID_USER, TableName.USER,
            ColumnName.USER_ID_USERS, TableName.LIBRARY, TableName.ORDER, ColumnName.ORDER_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.BOOK, ColumnName.BOOK_ID_BOOK,
            TableName.ORDER, ColumnName.ORDER_ID_BOOK, TableName.ORDER_STATUS, TableName.ORDER,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER_STATUS,
            ColumnName.ORDER_STATUS_STATUS, TableName.ORDER, ColumnName.ORDER_ID_REQUEST);

    private final static String GET_ORDER_BY_CITY_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s.%s=? group by %s.%s",
            TableName.ORDER, TableName.USER, TableName.ORDER, ColumnName.ORDER_ID_USER, TableName.USER,
            ColumnName.USER_ID_USERS, TableName.LIBRARY, TableName.ORDER, ColumnName.ORDER_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.BOOK, ColumnName.BOOK_ID_BOOK,
            TableName.ORDER, ColumnName.ORDER_ID_BOOK, TableName.ORDER_STATUS, TableName.ORDER,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_ID_STATUS, TableName.LIBRARY,
            ColumnName.LIBRARY_CITY, TableName.ORDER, ColumnName.ORDER_ID_REQUEST);

    private final static String GET_ORDER_BY_CITY_AND_STATUS_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s.%s=? and %s.%s=? group by %s.%s",
            TableName.ORDER, TableName.USER, TableName.ORDER, ColumnName.ORDER_ID_USER, TableName.USER,
            ColumnName.USER_ID_USERS, TableName.LIBRARY, TableName.ORDER, ColumnName.ORDER_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.BOOK, ColumnName.BOOK_ID_BOOK,
            TableName.ORDER, ColumnName.ORDER_ID_BOOK, TableName.ORDER_STATUS, TableName.ORDER,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_ID_STATUS, TableName.LIBRARY,
            ColumnName.LIBRARY_CITY, TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_STATUS, TableName.ORDER, ColumnName.ORDER_ID_REQUEST);

    private final static String GET_ALL_ORDERS_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) group by %s.%s",
            TableName.ORDER, TableName.USER, TableName.ORDER, ColumnName.ORDER_ID_USER, TableName.USER,
            ColumnName.USER_ID_USERS, TableName.LIBRARY, TableName.ORDER, ColumnName.ORDER_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.BOOK, ColumnName.BOOK_ID_BOOK,
            TableName.ORDER, ColumnName.ORDER_ID_BOOK, TableName.ORDER_STATUS, TableName.ORDER,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER, ColumnName.ORDER_ID_REQUEST);


    @Override
    public Optional<OrderDto> getOrderById(Long id) throws DAOException {
        logger.info("Receiving a order by id.");
        OrderDtoMapper mapper = new OrderDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<OrderDto> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_ORDER_BY_ID_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("OrderBookDto by id received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DAOException("Find more 1 order.");
            }
        } catch (SQLException sqlE) {
            logger.error("Find more 1 request. order - {}", entity.toString());
            throw new DAOException("Find more 1 order", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<OrderDto> getOrderByCity(String city) throws DAOException {
        logger.info("Receiving a request by city.");
        OrderDtoMapper mapper = new OrderDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<OrderDto> orderDtos = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_ORDER_BY_CITY_QUERY, city);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                orderDtos.add(mapper.map(resultSet));
            }
            logger.info("Receiving the list of orderBookDtos by city is over.");
            return orderDtos;
        } catch (SQLException sqlE) {
            logger.error("OrderBookDto list by city not received. Library - {}", city.toString());
            throw new DAOException("OrderBookDto list by city not received", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<OrderDto> getOrderByStatus(OrderStatus status) throws DAOException {
        logger.info("Receiving a orders by status.");
        OrderDtoMapper mapper = new OrderDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<OrderDto> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_ORDER_BY_STATUS_QUERY, status.name());
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(mapper.map(resultSet));
            }
            logger.info("Receiving the list of orders by status is over.");
            return orders;
        } catch (SQLException sqlE) {
            logger.error("OrderBookDto list by status not received. Status - {}", status);
            throw new DAOException("OrderBookDto list by status not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<OrderDto> getOrderByUser(long id) throws DAOException {
        logger.info("Receiving orders by user.");
        OrderDtoMapper mapper = new OrderDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<OrderDto> orderDtos = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_ORDER_BY_USER_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                orderDtos.add(mapper.map(resultSet));
            }
            logger.info("Receiving the list of orderBookDtos by user is over.");
            return orderDtos;
        } catch (SQLException sqlE) {
            logger.error("OrderBookDto list by user not received. User id - {}", id);
            throw new DAOException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<OrderDto> getOrders() throws DAOException {
        logger.info("Getting a list of orderBookDtos");
        List<OrderDto> orderDtos = new ArrayList<>();
        OrderDtoMapper mapper = new OrderDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = connection.prepareStatement(GET_ALL_ORDERS_QUERY);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                orderDtos.add(mapper.map(resultSet));
            }
            logger.info("OrderBookDto list received.");
            return orderDtos;
        } catch (SQLException sqlE) {
            logger.error("OrderBookDto list not received... Error.");
            throw new DAOException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<OrderDto> getOrderByCityAndStatus(String city, OrderStatus status) throws DAOException {
        logger.info("Receiving orders by city and by status.");
        OrderDtoMapper mapper = new OrderDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<OrderDto> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_ORDER_BY_CITY_AND_STATUS_QUERY, city, status.name());
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(mapper.map(resultSet));
            }
            logger.info("Receiving the list of orders by city and by status is over.");
            return orders;
        } catch (SQLException sqlE) {
            logger.error("OrderBookDto list by city and by status not received. Status - {}, city - {}", status, city);
            throw new DAOException("OrderBookDto list by city and by status not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }
}
