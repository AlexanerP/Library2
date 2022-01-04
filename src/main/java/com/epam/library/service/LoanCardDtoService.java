package com.epam.library.service;

import com.epam.library.entity.LoanCardStatus;
import com.epam.library.entity.dto.LoanCardDto;

import java.util.List;
import java.util.Optional;

public interface LoanCardDtoService {

    List<LoanCardDto> showCardsByStatus(String status) throws ServiceException;

    List<LoanCardDto> showCardsByUser(String userId) throws ServiceException;

    List<LoanCardDto> showCardsByBook(String bookId) throws ServiceException;

    List<LoanCardDto> showCardsByCityAndStatus(String city, String status) throws ServiceException;

    List<LoanCardDto> showCardsByCity(String city) throws ServiceException;

    List<LoanCardDto> showAll() throws ServiceException;

    Optional<LoanCardDto> showCardsById(String loanCardId) throws ServiceException;

}
