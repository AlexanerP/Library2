package com.epam.library.service.impl;

import com.epam.library.service.BookService;
import com.epam.library.service.ServiceException;

public class BookServiceImpl implements BookService {

    @Override
    public long getCountBooks() throws ServiceException {
        return 55;
    }
}
