package com.epam.library.dao;

import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean create(User user) throws DaoException;

    int update(User user) throws DaoException;

    int updatePassword(User user) throws DaoException;

    Optional<User> getUserById(long id) throws DaoException;

    List<User> getUserByEmail(String email) throws DaoException;

    Optional<User> getUserByEmailAndPassword(String email, String password) throws DaoException;

    int delete(User user) throws DaoException;

    List<User> getUsers() throws DaoException;

    List<User> getUsersByStatus(UserStatus status) throws DaoException;

    List<User> getUsersByRole(UserRole role) throws DaoException;

    long getCountByStatus(UserStatus status) throws DaoException;

}
