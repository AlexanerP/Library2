package com.epam.library.service.impl;

import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Test
    void verification() {
        try {
            String email = "email";
            String password = "password";
            Optional<User> optionalUser = userService.verification(email, password);
            assertTrue(optionalUser.isPresent());
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void create() {
        try {
            String email = "email@gamil.com";
            String password = "Password1";
            String secondName = "secondName";
            String lastName = "lastName";
            int expected = 1;
            int actual = userService.create(email, password, secondName, lastName);
            assertEquals(expected, actual);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUsers() {
        try {
            List<User> users = userService.getUsers();
            outPut(users);
            assertFalse(users.isEmpty());
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getCountUsers() {
        try {
            long count = userService.showCountUsers();
            System.out.println(count);
            assertTrue(count > 0);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showUserByStatus() {
        try {
            String status = "active";
            List<User> users = userService.showUserByStatus(status);
            outPut(users);
            assertFalse(users.isEmpty());
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showUserByRole() {
        try {
            String role = "admin";
            List<User> users = userService.showUserByRole(role);
            outPut(users);
            assertFalse(users.isEmpty());
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showUserByEmail() {
        try {
            String email = "admin@gmail.com";
            List<User> users = userService.showUserByEmail(email);
            outPut(users);
            assertFalse(users.isEmpty());
        }catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    @Test
    void showUserById() {
        try {
            String userId = "1";
            Optional<User> optionalUser = userService.showUserById(userId);
            assertTrue(optionalUser.isPresent());
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void update() {
        try {
            String userId = "1";
            String email = "email@gamil.com";
            String secondName = "";
            String lastName = "1";

            boolean condition = userService.update(email, secondName, lastName, userId);

            assertTrue(condition);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateStatus() {
        try {
            String status = "delete";
            String userId = "1";
            boolean condition = userService.updateStatus(userId, status);
            assertTrue(condition);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateRole() {
        try {
            String role = "manager";
            String userId = "1";
            boolean condition = userService.updateRole(userId, role);
            assertTrue(condition);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updatePassword() {
        try {
            String password = "Manager1";
            String email = "1";
            String oldPassword = "1";
            boolean condition = userService.updatePassword(password, email, oldPassword);
            assertTrue(condition);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private void outPut(List<User> list) {
        for (User user : list) {
            System.out.println(user);
        }
    }
}