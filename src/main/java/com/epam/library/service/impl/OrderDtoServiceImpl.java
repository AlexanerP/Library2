package com.epam.library.service.impl;

import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDtoServiceImpl implements OrderDtoService {

    @Override
    public boolean create(long bookId, long userId, String city) throws ServiceException {
        System.out.println("bookId/" + bookId + ". userId/" + userId + ". city/" + city);
        return true;
    }

    @Override
    public List<OrderDto> showOrdersUser(long userId) throws ServiceException {
        System.out.println("userId/" + userId);

        List<OrderDto> orderDtos = new ArrayList<>();

        for (int i = 0; i < 33; i++) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderDtoId(i);
            orderDto.setBookId(i +1);
            orderDto.setUserId(i + 2);
            orderDto.setAdminId(i);
            orderDto.setTitle("Test title");
            orderDto.setIsbn("Test ISBN");
            orderDto.setYear("Year");
            orderDto.setDate(LocalDate.now());
            orderDto.setEmail("Test email");
            orderDto.setCountViolations(3);
            orderDto.setCityLibrary("Minsk");
            orderDto.setStatus(OrderStatus.CONSIDERATION);
            orderDto.setComment("-");

            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public List<OrderDto> getOrders() throws ServiceException {
        System.out.println("RequestMaterialServiceImpl");
        List<OrderDto> requests = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            OrderDto request = new OrderDto();
            request.setOrderDtoId(Long.valueOf(i));
            request.setAdminId(Long.valueOf(0));
            request.setUserId(Long.valueOf(10));
            request.setBookId(Long.valueOf(2));
            request.setTitle("title");
            request.setIsbn("isbn");
            request.setYear("year");
//            request.setDate("LocalDateTime.now()");
            request.setCityLibrary("city");
            request.setComment("comment");
            request.setStatus(OrderStatus.DELETE);
            requests.add(request);
        }
        return requests;
    }
}
