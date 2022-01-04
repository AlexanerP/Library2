package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.IOException;
import java.util.List;

public class AdminPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            List<OrderDto> countOrders = orderDtoService.showOrdersByStatus(OrderStatus.OPENED.name());
            req.setAttribute("countOrders", countOrders.size());
            req.getRequestDispatcher(PathFile.HOME_ADMIN_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
