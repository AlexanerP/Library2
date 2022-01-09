package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionOrderCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionOrderCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            String orderId = req.getParameter("orderId");
            String status = req.getParameter("status");
            User user = (User) req.getSession().getAttribute("user");
            if (orderId != null && status != null) {
                boolean resultOperation = orderService.updateStatus(orderId, status, user.getUserId() + "");
                if (resultOperation) {
                    String successfulMessage = "Operation successful";
                    req.getSession().setAttribute("successfulMessage", successfulMessage);
                    resp.sendRedirect("Controller?command=GoToMessagePage");
                } else {
                    String negativeMessage = "Operation failed";
                    req.getSession().setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=GoToMessagePage");
                }
            } else {
                req.getRequestDispatcher(PathJsp.ORDERS_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error updating book order status.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
