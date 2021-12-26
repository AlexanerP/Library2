package com.epam.library.dao;


import com.epam.library.entity.dto.OrderDto;
import com.epam.library.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderBookDtoDao {

    Optional<OrderDto> getRequestById(Long id) throws DAOException;

    List<OrderDto> getRequestsByCity(String city) throws DAOException;

    List<OrderDto> getRequestsByStatus(OrderStatus status) throws DAOException;

    List<OrderDto> getRequestsByUser(Long id) throws DAOException;

    List<OrderDto> getRequests() throws DAOException;

}
