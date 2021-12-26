package com.epam.library.service.impl;

import com.epam.library.service.AuthorService;
import com.epam.library.service.ServiceException;

public class AuthorServiceImpl implements AuthorService {
    @Override
    public int getCountAuthors() throws ServiceException {
        return 33;
    }
}
