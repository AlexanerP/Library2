package com.epam.library.service;

import com.epam.library.entity.dto.LoanCardDto;

import java.util.List;

public interface LoanCardService {

    List<LoanCardDto> showCardsByUser(long idUser) throws ServiceException;

}
