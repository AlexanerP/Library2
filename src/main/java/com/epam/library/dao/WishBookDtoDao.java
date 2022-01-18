package com.epam.library.dao;

import com.epam.library.entity.dto.WishBookDto;

import java.util.List;

public interface WishBookDtoDao {

    List<WishBookDto> getBooks(long userId) throws DaoException;

}
