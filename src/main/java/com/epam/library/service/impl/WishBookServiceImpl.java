package com.epam.library.service.impl;

import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.entity.dto.WishBookDto;
import com.epam.library.service.ServiceException;
import com.epam.library.service.WishBookService;

import java.util.ArrayList;
import java.util.List;

public class WishBookServiceImpl implements WishBookService {

    @Override
    public List<WishBookDto> showBooksByUser(long userId) throws ServiceException {
        List<WishBookDto> books = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            WishBookDto bookDTO = new WishBookDto();
            bookDTO.setBookId(1);

            bookDTO.setTitle("Wish Book");
            bookDTO.setIsbn("Wish Book");
            bookDTO.setPublisher("publisher");
            bookDTO.setYear("2000");

            books.add(bookDTO);
        }
        return books;
    }
}
