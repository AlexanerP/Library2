package com.epam.library.service;

public interface LoanCardService {

    boolean create(String orderId, String typeUse) throws ServiceException;

    boolean closeLoanCard(String cardId) throws ServiceException;

    boolean closeLoanCardWithViolation(String cardId) throws ServiceException;

    boolean update(String cardId, String typeUse, String status) throws ServiceException;

    long showCountCards(String status) throws ServiceException;
}
