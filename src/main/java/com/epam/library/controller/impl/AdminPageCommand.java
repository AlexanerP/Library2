package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.IOException;

public class AdminPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            int countOrders = orderService.getCountOrders();
            req.setAttribute("countOrders", countOrders);
            req.getRequestDispatcher(PathFile.HOME_ADMIN_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
