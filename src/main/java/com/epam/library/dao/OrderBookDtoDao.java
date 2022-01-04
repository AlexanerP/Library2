package com.epam.library.dao;


import com.epam.library.entity.dto.OrderDto;
import com.epam.library.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderBookDtoDao {

    Optional<OrderDto> getOrderById(Long id) throws DAOException;

    List<OrderDto> getOrderByCity(String city) throws DAOException;

    List<OrderDto> getOrderByCityAndStatus(String city, OrderStatus status) throws DAOException;

    List<OrderDto> getOrderByStatus(OrderStatus status) throws DAOException;

    List<OrderDto> getOrderByUser(long id) throws DAOException;

    List<OrderDto> getOrders() throws DAOException;

}
