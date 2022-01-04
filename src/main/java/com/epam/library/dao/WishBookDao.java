package com.epam.library.dao;

import com.epam.library.entity.WishBook;

import java.util.Optional;

public interface WishBookDao {

    boolean create(WishBook wishBook) throws DAOException;

    int delete(long wishBookId) throws DAOException;

    long getCountWishBooks() throws DAOException;

    Optional<WishBook> getWishBookByBookAndByUser(long bookId, long userId);

    Optional<WishBook> getWishBookByID(long wishBookId);
}
