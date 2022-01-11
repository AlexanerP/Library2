package com.epam.library.dao;

import com.epam.library.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LibraryDao {

    boolean create(Library library) throws DAOException;

    int update(Library library) throws DAOException;

    Optional<Library> getLibraryById(Long id) throws DAOException;

    Optional<Library> getLibraryByCity(String city) throws DAOException;

    List<Library> getLibraries() throws DAOException;

    List<Library> getLibrariesByStatus(LibraryStatus status) throws DAOException;

}
