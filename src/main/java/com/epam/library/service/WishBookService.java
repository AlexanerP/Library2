package com.epam.library.service;

import com.epam.library.entity.dto.WishBookDto;

import java.util.List;

public interface WishBookService {

    List<WishBookDto> showBooksByUser(long userId) throws ServiceException;
}
