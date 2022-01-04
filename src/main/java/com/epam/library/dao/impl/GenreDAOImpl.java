package com.epam.library.dao.impl;

import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOHelper;
import com.epam.library.dao.GenreDAO;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.GenreMapper;
import com.epam.library.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDAOImpl extends DAOHelper implements GenreDAO {

    private static final Logger logger = LoggerFactory.getLogger(GenreDAOImpl.class);

    private final static String GET_GENRES_BY_ID_BOOK_QUERY = String.format("SELECT %s.%s, %s.%s, %s.%s FROM %s " +
                    "INNER JOIN %s USING(%s) INNER JOIN %s USING(%s) WHERE %s.%s=?", TableName.G_H_B,
            ColumnName.GHB_ID_GENRES, TableName.G_H_B, ColumnName.GHB_ID_BOOK, TableName.GENRES,
            ColumnName.GENRES_GENRE, TableName.BOOK, TableName.G_H_B, ColumnName.GHB_ID_BOOK, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.BOOK, ColumnName.BOOK_ID_BOOK);

    private static final String ADD_GENRE_QUERY = String.format("INSERT IGNORE INTO %s(%s) VALUES(?)",
            TableName.GENRES, ColumnName.GENRES_GENRE);

    private final static String UPDATE_GENRE_BY_ID_QUERY = String.format("UPDATE %s SET %s=? WHERE %s=?;",
            TableName.GENRES, ColumnName.GENRES_GENRE, ColumnName.GENRES_ID_GENRE);

    private final static String GET_ALL_GENRES_QUERY = String.format("SELECT * FROM %s order by %s;", TableName.GENRES,
            ColumnName.GENRES_ID_GENRE);

    private final static String GET_COUNT_QUERY = String.format("select count(%s) from %s", ColumnName.GENRES_GENRE,
            TableName.GENRES);

    private static final String DELETE_GENRES_QUERY = String.format("delete from %s where %s=?",
            TableName.G_H_B, ColumnName.GHB_ID_BOOK);

    @Override
    public boolean create(Genre genre) throws DAOException {
        logger.info("Start adding genres to the table.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, ADD_GENRE_QUERY, genre.getCategory());
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Genre of the book is not added to the table. Genre - {}", genre);
            throw new DAOException("Genre of the book is not added to the table.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public boolean update(Genre genre) throws DAOException {
        logger.info("Start to update genre by id.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_GENRE_BY_ID_QUERY, genre.getCategory(),
                    genre.getGenreId());
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("No genre update by id. Genre - {}", genre.toString());
            throw new DAOException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public boolean deleteGenreByBookId(long bookId)  {
        logger.info("Deleting genre data by book. BookId - {}", bookId);
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, DELETE_GENRES_QUERY, bookId);
            prStatement.executeUpdate();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Error while deleting genre data by book. BookDto id - {}", bookId);
            throw new DAOException("Error while deleting genre data by book.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<Genre> getGenresByIdBook(Long bookId) throws DAOException {
        logger.info("Receiving a genre by bookId.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        GenreMapper mapper = new GenreMapper();
        List<Genre> genres = new ArrayList<>();
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_GENRES_BY_ID_BOOK_QUERY, bookId);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(mapper.map(resultSet));
            }

        } catch (SQLException sqlE) {
            logger.error("Find more 1 author by bookId. ");
            throw new DAOException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return genres;
    }

    @Override
    public List<Genre> getGenres() throws DAOException {
        logger.info("Getting a list of genres.");
        List<Genre> genres = new ArrayList<>();
        PreparedStatement prStatement = null;
        GenreMapper mapper = new GenreMapper();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = connection.prepareStatement(GET_ALL_GENRES_QUERY);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(mapper.map(resultSet));
            }
        }catch (SQLException sqlE) {
            logger.error("Genres not received.");
            throw new DAOException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Authors list received.");
        return genres;
    }

    @Override
    public int getCount() throws DAOException {
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        int countAuthors = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = connection.prepareStatement(GET_COUNT_QUERY);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                countAuthors = resultSet.getInt(1);
            }
        }catch (SQLException sqlE) {
            logger.error("Number of genres not received.");
            throw new DAOException("Number of genres not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return countAuthors;
    }
}
