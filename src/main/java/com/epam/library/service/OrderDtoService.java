package com.epam.library.service;

import com.epam.library.entity.Library;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderDtoService {

    List<OrderDto> showOrdersUser(String userId) throws ServiceException;

    Optional<OrderDto> showOrderById(String orderId) throws ServiceException;

    List<OrderDto> showAllOrders() throws ServiceException;

    List<OrderDto> showOrdersByStatus(String status) throws ServiceException;

    List<OrderDto> showOrdersByCityAndStatus(String city, String status) throws ServiceException;

    List<OrderDto> showOrdersByCity(String city) throws ServiceException;

}
