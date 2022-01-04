package com.epam.library.dao;

import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;

import java.util.List;
import java.util.Optional;

public interface LoanCardDtoDao {

    List<LoanCardDto> getCardsByIdUser(long id) throws DAOException;

    List<LoanCardDto> getCardsByStatusCard(LoanCardStatus status) throws DAOException;

    List<LoanCardDto> getCardsByIdBook(long id) throws DAOException;

    Optional<LoanCardDto> getCardsById(long id) throws DAOException;

    List<LoanCardDto> getCardsByCity(String city) throws DAOException;

    List<LoanCardDto> getCardsByCityAndStatus(String city, LoanCardStatus status) throws DAOException;

    List<LoanCardDto> getAll() throws DAOException;
}
