package com.epam.library.service;

import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> verification(String email, String password) throws ServiceException;

    boolean remove(String userId) throws ServiceException;

    int create(String email, String password, String secondName, String lastName) throws ServiceException;

    List<User> getUsers() throws ServiceException;

    long showCountByStatus(String status) throws ServiceException;

    List<User> showUserByStatus(String status) throws ServiceException;

    List<User> showUserByRole(String role) throws ServiceException;

    List<User> showUserByEmail(String email) throws ServiceException;

    Optional<User> showUserById(String userId) throws ServiceException;

    int update(String email, String secondName, String lastName, String userId) throws ServiceException;

    int updatePassword(String newPassword, String email, String oldPassword) throws ServiceException;

    boolean updateStatus(String userId, String status) throws ServiceException;

    boolean updateRole(String userId, String role) throws ServiceException;

    boolean addViolation(String userId) throws ServiceException;

    boolean removeViolation(String userId) throws ServiceException;
}
