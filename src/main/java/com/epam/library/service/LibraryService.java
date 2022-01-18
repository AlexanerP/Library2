package com.epam.library.service;

import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    int create(String city, String street) throws ServiceException;

    List<Library> showAll() throws ServiceException;

    Optional<Library> showById(String libraryId) throws ServiceException;

    Optional<Library> showByCity(String city) throws ServiceException;

    List<Library> showByStatus(String status) throws ServiceException;

    boolean updateStatus(String libraryId, String status) throws ServiceException;

    int update(String libraryId, String city, String street) throws ServiceException;
}
