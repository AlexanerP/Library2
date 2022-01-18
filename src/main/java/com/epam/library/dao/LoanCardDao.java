package com.epam.library.dao;

import com.epam.library.entity.LoanCard;
import com.epam.library.entity.LoanCardStatus;

import java.util.Optional;

public interface LoanCardDao {

    boolean create(LoanCard card) throws DaoException;

    Optional<LoanCard> getCardById(long cardId) throws DaoException;

    boolean update(LoanCard card) throws DaoException;

    long getCountCardsByStatus(LoanCardStatus status) throws DaoException;
}
