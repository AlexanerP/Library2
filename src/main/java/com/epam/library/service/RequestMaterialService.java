package com.epam.library.service;

import com.epam.library.entity.dto.OrderDto;

import java.util.List;

public interface RequestMaterialService {

    List<OrderDto> getRequests();
}
