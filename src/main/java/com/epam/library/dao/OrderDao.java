package com.epam.library.dao;

import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    boolean create(Order order) throws DAOException;

    int update(Order order) throws DAOException;

    int updateStatus(Long id, OrderStatus status) throws DAOException;

    int delete(Long id) throws DAOException;

    Optional<Order> getOrderById(Long id) throws DAOException;

    List<Order> getOrders() throws DAOException;

}
