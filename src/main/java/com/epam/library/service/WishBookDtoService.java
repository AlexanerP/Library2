package com.epam.library.service;

import com.epam.library.entity.dto.WishBookDto;

import java.util.List;

public interface WishBookDtoService {

    List<WishBookDto> showBooksUser(String userId) throws ServiceException;
}
