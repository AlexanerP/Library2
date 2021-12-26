package com.epam.library.dao;

import com.epam.library.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusDAO {

    boolean create(String status) throws DAOException;

    int delete(Integer id) throws DAOException;

    int update(Status status) throws DAOException;

    Optional<Status> getStatus(Integer id) throws DAOException;

    List<Status> getStatuses() throws DAOException;
}
