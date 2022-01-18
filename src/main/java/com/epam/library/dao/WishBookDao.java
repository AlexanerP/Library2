package com.epam.library.dao;

import com.epam.library.entity.WishBook;

import java.util.Optional;

public interface WishBookDao {

    boolean create(WishBook wishBook) throws DaoException;

    int delete(long wishBookId) throws DaoException;

    long getCountWishBooks() throws DaoException;

    Optional<WishBook> getWishBookByBookAndByUser(long bookId, long userId);

    Optional<WishBook> getWishBookByID(long wishBookId);
}
