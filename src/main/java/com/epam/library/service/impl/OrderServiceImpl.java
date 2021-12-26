package com.epam.library.service.impl;

import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;

public class OrderServiceImpl implements OrderService {

    @Override
    public int getCountOrders() throws ServiceException {
        return 111;
    }
}
