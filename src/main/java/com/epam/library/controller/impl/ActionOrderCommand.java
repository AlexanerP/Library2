package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionOrderCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
//            String orderId = req.getParameter("orderId");
//            String status = req.getParameter("status");
//            String message = "Order not updated";
//            if (orderId != null && orderId != "" && status != null && status != "") {
//                orderDtoService.update(orderId, status);
//                message = "Order status update";
//            }
//
//            resp.sendRedirect("Controller?command=GoToMessagePage&message=" + message);
//        }catch (ServiceException e) {
//
//        }
    }
}
