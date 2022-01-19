package com.epam.library.dao;


import com.epam.library.entity.dto.OrderDto;
import com.epam.library.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDtoDao {

    Optional<OrderDto> getOrderById(Long id) throws DaoException;

    List<OrderDto> getOrderByCity(String city) throws DaoException;

    List<OrderDto> getOrderByCityAndStatus(String city, OrderStatus status) throws DaoException;

    List<OrderDto> getOrderByStatus(OrderStatus status) throws DaoException;

    List<OrderDto> getOrderByUser(long id) throws DaoException;

    List<OrderDto> getOrders() throws DaoException;

}
