package com.epam.library.dao;

import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDAO {

    boolean create(User user) throws DAOException;

    int update(User user) throws DAOException;

    Optional<User> getUserById(long id) throws DAOException;

    List<User> getUserByEmail(String email) throws DAOException;

    Optional<User> getUserByEmailAndPassword(String email, String password) throws DAOException;

    int delete(User user) throws DAOException;

    List<User> getUsers() throws DAOException;

    List<User> getUsersByStatus(UserStatus status) throws DAOException;

    List<User> getUsersByRole(UserRole role) throws DAOException;

    int getCount(UserStatus status) throws DAOException;

    List<User> getUsersByPeriod(LocalDate start, LocalDate finish);
}
