package com.epam.library.service;

import com.epam.library.entity.dto.OrderDto;

import java.util.List;

public interface OrderDtoService {

    boolean create(long bookId, long userId, String city) throws ServiceException;

    List<OrderDto> showOrdersUser(long userId) throws ServiceException;

    List<OrderDto> getOrders() throws ServiceException;
}
