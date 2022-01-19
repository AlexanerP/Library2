package com.epam.library.dao;

import com.epam.library.entity.*;

import java.util.List;
import java.util.Optional;

public interface LibraryDao {

    boolean create(Library library) throws DaoException;

    int update(Library library) throws DaoException;

    Optional<Library> getLibraryById(long id) throws DaoException;

    Optional<Library> getLibraryByCity(String city) throws DaoException;

    List<Library> getLibraries() throws DaoException;

    List<Library> getLibrariesByStatus(LibraryStatus status) throws DaoException;

}
