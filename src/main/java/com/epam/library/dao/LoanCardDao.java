package com.epam.library.dao;

import com.epam.library.entity.LoanCard;
import com.epam.library.entity.LoanCardStatus;

import java.util.Optional;

public interface LoanCardDao {

    boolean create(LoanCard card) throws DAOException;

    Optional<LoanCard> getCardById(long cardId) throws DAOException;

    boolean update(LoanCard card) throws DAOException;

    long getCountCardsByStatus(LoanCardStatus status) throws DAOException;
}
