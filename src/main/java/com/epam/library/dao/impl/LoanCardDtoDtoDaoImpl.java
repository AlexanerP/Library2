package com.epam.library.dao.impl;

import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOHelper;
import com.epam.library.dao.LoanCardDtoDao;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.LoanCardDtoMapper;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanCardDtoDtoDaoImpl extends DAOHelper implements LoanCardDtoDao {

    private static final Logger logger = LoggerFactory.getLogger(LoanCardDtoDtoDaoImpl.class);

    private final static String GET_ALL_CARDS_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) group by %s.%s", TableName.LOAN_CARDS, TableName.LC_H_U, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_CARD, TableName.LC_H_U, ColumnName.LCHU_ID_CARD, TableName.USER, TableName.LC_H_U,
            ColumnName.LCHU_ID_USER, TableName.USER, ColumnName.USER_ID_USERS, TableName.LIBRARY,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY,
            TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK, TableName.BOOK,
            ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_STATUS,
            TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_ID_BOOK_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.LC_H_U,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD, TableName.LC_H_U, ColumnName.LCHU_ID_CARD,
            TableName.USER, TableName.LC_H_U, ColumnName.LCHU_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);


    private final static String GET_CARDS_BY_CITY_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.LC_H_U,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD, TableName.LC_H_U, ColumnName.LCHU_ID_CARD,
            TableName.USER, TableName.LC_H_U, ColumnName.LCHU_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.LIBRARY, ColumnName.LIBRARY_CITY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_ID_USER_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.LC_H_U,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD, TableName.LC_H_U, ColumnName.LCHU_ID_CARD,
            TableName.USER, TableName.LC_H_U, ColumnName.LCHU_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.USER, ColumnName.USER_ID_USERS, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_STATUS_CARD_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.LC_H_U,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD, TableName.LC_H_U, ColumnName.LCHU_ID_CARD,
            TableName.USER, TableName.LC_H_U, ColumnName.LCHU_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_STATUS, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_ID_CARD_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.LC_H_U,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD, TableName.LC_H_U, ColumnName.LCHU_ID_CARD,
            TableName.USER, TableName.LC_H_U, ColumnName.LCHU_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    private final static String UPDATE_LOAN_CARD_QUERY = String.format("Update %s SET %s=(SELECT %s from %s where %s=?), %s=?, %s=?, %s=?, %s=?, " +
                    "%s=?, %s=(SELECT %s from %s where %s=?) where %s=?", TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, ColumnName.CARD_STATUS_ID_STATUS,  TableName.LOAN_CARDS_STATUS,
            ColumnName.CARD_STATUS_STATUS, ColumnName.LOAN_CARD_TAKING_BOOK, ColumnName.LOAN_CARD_RETURN_BOOK,
            ColumnName.LOAN_DEADLINE, ColumnName.LOAN_CARD_TYPE_USE, ColumnName.LOAN_CARD_ID_BOOK,
            ColumnName.LOAN_CARD_ID_LIBRARY, ColumnName.LIBRARY_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_CITY, ColumnName.LOAN_CARD_ID_CARD);

    private final static String UPDATE_AUXILIARY_TABLE_USER_QUERY = String.format("Update %s SET %s=? where %s=?",
            TableName.LC_H_U, ColumnName.LCHU_ID_USER, ColumnName.LCHU_ID_CARD);


    private final static String ADD_CARD_HAS_USER_QUERY = String.format("INSERT INTO %s(%s, %s) VALUES(?, ?)",
            TableName.LC_H_U, ColumnName.LCHU_ID_CARD, ColumnName.LCHU_ID_USER);

    private final static String ADD_PERSONAL_CARD = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s)" +
                    " values(?, ?, ?, ?, ?, (SELECT %s FROM %s WHERE %s=?), (SELECT %s FROM %s WHERE %s=?));",
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_TAKING_BOOK, ColumnName.LOAN_CARD_RETURN_BOOK,
            ColumnName.LOAN_DEADLINE, ColumnName.LOAN_CARD_TYPE_USE, ColumnName.LOAN_CARD_ID_BOOK,
            ColumnName.LOAN_CARD_ID_STATUS, ColumnName.LOAN_CARD_ID_LIBRARY, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_STATUS, ColumnName.LIBRARY_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_CITY);

    private final static String UPDATE_STATUS_QUERY = String.format("UPDATE %s SET %s=(SELECT %s FROM %s WHERE %s=?)" +
                    " WHERE %s=?;", TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_STATUS,
            ColumnName.CARD_STATUS_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_STATUS,
            ColumnName.LOAN_CARD_ID_CARD);

    @Override
    public boolean create(LoanCardDto card) throws DAOException {
        logger.info("Loan card creating.");
        PreparedStatement prStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);

            prStatement = connection.prepareStatement(ADD_PERSONAL_CARD, Statement.RETURN_GENERATED_KEYS);
            prStatement.setObject(1, card.getTakingBook());
            prStatement.setObject(2, card.getReturnBook());
            prStatement.setObject(3, card.getDeadline());
            prStatement.setString(4, card.getTypeUse().name());
            prStatement.setLong(5, card.getBookId());
            prStatement.setString(6, card.getStatus().name());
            prStatement.setString(7, card.getCityLibrary());


            int cardId = 0;
            int rowExecute = prStatement.executeUpdate();
            if (rowExecute == 0) {
                throw new DAOException("Creating loan card failed, no rows affected.");
            }
            resultSet = prStatement.getGeneratedKeys();
            if (resultSet.next()) {
                cardId = resultSet.getInt(1);
            }

            prStatement = createPreparedStatement(connection, ADD_CARD_HAS_USER_QUERY,
                    cardId, card.getUserId());
            prStatement.execute();

            connection.commit();
            logger.info("Loan card created...");
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException sqlE) {
            try {
                connection.rollback();
            } catch (SQLException sql) {
                logger.error("Rollback doesn't try.");
            }
            logger.error("An error occured while creating the loan card. Loan card - {}.", card.toString());
            throw new DAOException("Failed to create personal card", sqlE);
        } finally {
            closePreparedStatement(prStatement);
            closeResultSet(resultSet);
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @Override
    public boolean update(LoanCardDto card) throws DAOException {
        logger.info("Update loan card.");
        Connection connection = null;
        PreparedStatement prStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);

            prStatement = createPreparedStatement(connection, UPDATE_LOAN_CARD_QUERY, card.getStatus().name(),
                    card.getTakingBook(), card.getReturnBook(), card.getDeadline(), card.getTypeUse().name(),
                    card.getBookId(), card.getCityLibrary(), card.getLoanCardDtoId());

            prStatement.executeUpdate();

            prStatement = createPreparedStatement(connection, UPDATE_AUXILIARY_TABLE_USER_QUERY, card.getUserId(),
                    card.getLoanCardDtoId());

            prStatement.executeUpdate();

            connection.commit();
            logger.info("Loan card updated...");
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException sqlE) {
            try {
                connection.rollback();
            } catch (SQLException sql) {
                logger.error("Rollback doesn't try.");
            }
            logger.error("Failed to update personal card. Personal card {}", card.toString());
            throw new DAOException("Failed to update personal card.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public boolean updateStatus(long cardId, LoanCardStatus status) throws DAOException {
        logger.info("Update status personal card.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, UPDATE_STATUS_QUERY, status.name(), cardId);
            prStatement.executeUpdate();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Failed to update status personal card. Id card - {}, status - {}", cardId, status.name());
            throw new DAOException("Failed to update status personal card." + cardId, sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<LoanCardDto> getCardsByIdUser(long id) throws DAOException {
        logger.info("Getting a list of user's cards by id user - {}", id);
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_ID_USER_QUERY, id);
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Personal cards by id user not received.");
            throw new DAOException("Personal cards by id user not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Personal cards by id user list received.");
        return cards;
    }

    @Override
    public List<LoanCardDto> getCardsByStatusCard(LoanCardStatus status) throws DAOException {
        logger.info("Getting a list of status cards by status - {}", status);
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_STATUS_CARD_QUERY, status.name());
            resultSet = prStatement.executeQuery();
            LoanCardDtoMapper mapper = new LoanCardDtoMapper();
            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Personal cards by status card not received.");
            throw new DAOException("Personal cards by status card not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Personal cards by status card received.");
        return cards;
    }

    @Override
    public List<LoanCardDto> getCardsByIdBook(long id) throws DAOException {
        logger.info("Getting a list of user's cards by id book - {}", id);
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_ID_BOOK_QUERY, id);
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Personal cards by id book not received.");
            throw new DAOException("Personal cards by id book not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Personal cards list by book received.");
        return cards;
    }

    @Override
    public List<LoanCardDto> getCardsByCity(String city) throws DAOException {
        logger.info("Getting a list of user's cards by city - {}", city);
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_CITY_QUERY, city);
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Personal cards by city not received.");
            throw new DAOException("Personal cards by city not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Personal cards list by city received.");
        return cards;
    }

    @Override
    public Optional<LoanCardDto> getCardsById(long id) throws DAOException {
        logger.info("Getting loan card by id.");
        LoanCardDtoMapper mapper = new LoanCardDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<LoanCardDto> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_ID_CARD_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }

            if(entity.size() == 1) {
                logger.info("Loan card received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                logger.info("Find more 1 loan card.");
                throw new UnsupportedOperationException("Find more 1 loan card.");
            }
        } catch (SQLException sqlE) {
            logger.error("Loan card not received.");
            throw new DAOException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<LoanCardDto> getAll() throws DAOException {
        logger.info("Getting a list of cards");
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_ALL_CARDS_QUERY);
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Personal cards not received.");
            throw new DAOException("Personal cards not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Personal cards list received.");
        return cards;
    }
}
