package com.epam.library.service;

import com.epam.library.entity.Library;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderDtoService {

    boolean create(long bookId, long userId, String city) throws ServiceException;

    List<OrderDto> showOrdersUser(long userId) throws ServiceException;

    Optional<OrderDto> showOrderById(long orderId) throws ServiceException;

    List<OrderDto> getOrders() throws ServiceException;

    List<OrderDto> showOrdersByStatus(OrderStatus status) throws ServiceException;

    List<OrderDto> showOrdersByCityAndStatus(String city, OrderStatus status) throws ServiceException;

    boolean update(String orderId, String status) throws ServiceException;

}
