package com.epam.library.dao;

import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;

import java.util.List;
import java.util.Optional;

public interface LoanCardDtoDao {

    boolean create(LoanCardDto card) throws DAOException;

    boolean update(LoanCardDto card) throws DAOException;

    boolean updateReturnDate(LoanCardDto card) throws DAOException;

    boolean updateStatus(long cardId, LoanCardStatus status) throws DAOException;

    List<LoanCardDto> getCardsByIdUser(long id) throws DAOException;

    List<LoanCardDto> getCardsByStatusCard(LoanCardStatus status) throws DAOException;

    List<LoanCardDto> getCardsByIdBook(long id) throws DAOException;

    Optional<LoanCardDto> getCardsById(long id) throws DAOException;

    List<LoanCardDto> getCardsByCity(String city) throws DAOException;

    List<LoanCardDto> getAll() throws DAOException;
}
