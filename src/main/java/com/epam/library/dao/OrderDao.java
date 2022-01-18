package com.epam.library.dao;

import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;

import java.util.Optional;

public interface OrderDao {

    boolean create(Order order) throws DaoException;

    int update(Order order) throws DaoException;

    int delete(long id) throws DaoException;

    long countOrderByStatus(OrderStatus status) throws DaoException;

    Optional<Order> getOrderById(long id) throws DaoException;

}
