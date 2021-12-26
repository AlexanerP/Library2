package com.epam.library.service.impl;

import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.ServiceException;
import com.epam.library.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {


    @Override
    public Optional<User> verification(String email, String password) throws ServiceException {
        System.out.println("Connection DB verification");
        User user1 = new User();
        user1.setUserId(1L);
        user1.setLibraryCity("Minsk");
        user1.setRole(UserRole.ADMIN);
        user1.setSecondName("Din W");
        user1.setLastName("Sa");
        user1.setEmail("email@gmail.com");
        user1.setStatus(UserStatus.ACTIVE);
        return Optional.of(user1);
    }

    @Override
    public boolean registration(String email, String password, String secondName, String lastName) throws ServiceException {
        System.out.println("email/" + email + " password/" + password + ". secondName/"+ secondName + ". LastName/" + lastName);
        return false;
    }

    @Override
    public List<User> getUsers() throws ServiceException{
        System.out.println("Connection DB getUsers");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setUserId(Long.valueOf(i));
            user.setSecondName("second name");
            user.setLastName("last name");
            user.setEmail("email");
            user.setCountViolations(5);
            user.setDateRegistration(LocalDate.now());
            user.setRole(UserRole.ADMIN);
            user.setStatus(UserStatus.ACTIVE);
            users.add(user);
        }
        return users;
    }

    @Override
    public long getCountUsers() throws ServiceException {
        return 77;
    }

    @Override
    public List<User> showUserByStatus(UserStatus status) throws ServiceException{
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 110; i++) {
            User user = new User();
            user.setUserId(Long.valueOf(i));
            user.setSecondName("second name");
            user.setLastName("last name");
            user.setEmail("status");
            user.setCountViolations(5);
            user.setDateRegistration(LocalDate.now());
            user.setRole(UserRole.ADMIN);
            user.setStatus(UserStatus.valueOf(status.name()));
            users.add(user);
        }
        return users;
    }

    @Override
    public List<User> showUserByRole(UserRole role) throws ServiceException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 110; i++) {
            User user = new User();
            user.setUserId(Long.valueOf(i));
            user.setSecondName("second name");
            user.setLastName("last name");
            user.setEmail("status");
            user.setCountViolations(5);
            user.setDateRegistration(LocalDate.now());
            user.setRole(UserRole.ADMIN);
            user.setStatus(UserStatus.ACTIVE);
            users.add(user);
        }
        return users;
    }

    @Override
    public Optional<User> showUserByEmail(String email) throws ServiceException {
        User user = new User();
        user.setUserId(Long.valueOf(555));
        user.setSecondName("second name");
        user.setLastName("last name");
        user.setEmail("ID");
        user.setCountViolations(5);
        user.setDateRegistration(LocalDate.now());
        user.setRole(UserRole.ADMIN);
        user.setStatus(UserStatus.ACTIVE);
        return Optional.of(user);
    }

    @Override
    public Optional<User> showUserById(long id) throws ServiceException {

            User user = new User();
            user.setUserId(Long.valueOf(55));
            user.setSecondName("second name");
            user.setLastName("last name");
            user.setEmail("ID");
            user.setCountViolations(5);
            user.setDateRegistration(LocalDate.now());
            user.setRole(UserRole.ADMIN);
            user.setStatus(UserStatus.ACTIVE);
        return Optional.of(user);
    }
}
