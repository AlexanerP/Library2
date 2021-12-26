package com.epam.library.service;

import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> verification(String email, String password) throws ServiceException;

    boolean registration(String email, String password, String secondName, String lastName) throws ServiceException;

    List<User> getUsers() throws ServiceException;

    long getCountUsers() throws ServiceException;

    List<User> showUserByStatus(UserStatus status) throws ServiceException;

    List<User> showUserByRole(UserRole role) throws ServiceException;

    Optional<User> showUserByEmail(String email) throws ServiceException;

    Optional<User> showUserById(long id) throws ServiceException;
}
