package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionUserOrderCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionUserOrderCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.ACTION_USER_ORDER);
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            String orderUserId = req.getParameter("orderUserId");
            String action = req.getParameter("action");
            if (orderUserId != null && action != null) {
                boolean resultOperation = orderService.delete(orderUserId);
                if (resultOperation) {
                    String successfulMessage = "Operation successful";
                    req.getSession().setAttribute("successfulMessage", successfulMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.ORDER_USER);
                } else {
                    String negativeMessage = "Operation failed";
                    req.getSession().setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher("Controller?command=" + CommandType.ORDER_USER).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error while deleting book order by user.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
