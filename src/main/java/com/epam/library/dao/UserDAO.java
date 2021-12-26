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

    int updateStatus(long id, UserStatus status) throws DAOException;

    Optional<User> getUserById(long id) throws DAOException;

    boolean isUserByEmail(String email) throws DAOException;

    Optional<User> getUserByEmailAndPassword(User user) throws DAOException;

    int delete(Long id) throws DAOException;

    List<User> getUsers() throws DAOException;

    List<User> getUsersByStatus(UserStatus status) throws DAOException;

    List<User> getUsersByRole(UserRole role) throws DAOException;

    int getCount(UserStatus status) throws DAOException;

    List<User> getUsersByPeriod(LocalDate start, LocalDate finish);
}
