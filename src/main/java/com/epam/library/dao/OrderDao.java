package com.epam.library.dao;

import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    boolean create(Order order) throws DAOException;

    int update(Order order) throws DAOException;

    int delete(long id) throws DAOException;

    long countOrderByStatus(OrderStatus status) throws DAOException;

    Optional<Order> getOrderById(long id) throws DAOException;

}
