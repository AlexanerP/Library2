package com.epam.library.service;

import com.epam.library.entity.Order;

import java.util.Optional;

public interface OrderService {

    boolean create(String bookId, String userId, String city, String comment) throws ServiceException;

    boolean updateStatus(String orderId, String status, String adminId) throws ServiceException;

    boolean update(String orderId, String comment, String city) throws ServiceException;

    boolean delete(String orderId) throws ServiceException;

    long showCountByStatus(String status) throws ServiceException;

    Optional<Order> showById(String orderId) throws ServiceException;
}
