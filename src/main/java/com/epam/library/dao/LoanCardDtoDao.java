package com.epam.library.dao;

import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;

import java.util.List;
import java.util.Optional;

public interface LoanCardDtoDao {

    List<LoanCardDto> getCardsByIdUser(long id) throws DaoException;

    List<LoanCardDto> getCardsByStatusCard(LoanCardStatus status) throws DaoException;

    List<LoanCardDto> getCardsByIdBook(long id) throws DaoException;

    Optional<LoanCardDto> getCardsById(long id) throws DaoException;

    List<LoanCardDto> getCardsByCity(String city) throws DaoException;

    List<LoanCardDto> getCardsByCityAndStatus(String city, LoanCardStatus status) throws DaoException;

    List<LoanCardDto> getAll() throws DaoException;
}
