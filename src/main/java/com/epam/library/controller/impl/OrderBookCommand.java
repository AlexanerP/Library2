package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
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

public class OrderBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.ORDER_BOOK);
            Long bookId = (Long) req.getSession().getAttribute("orderBookId");
            User user = (User) req.getSession().getAttribute("user");
            String library = req.getParameter("library");
            String comment = req.getParameter("comment");
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            req.getSession().removeAttribute("orderBookId");
            if (user != null && bookId != null && library != null) {
                if (orderService.create(bookId + "", user.getUserId() + "", library, comment)) {
                    String successfulMessage = "Operation successful";
                    req.getSession().setAttribute("successfulMessage", successfulMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    String negativeMessage = "Operation failed";
                    req.getSession().setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
            }
        }catch (ServiceException e) {
            logger.error("Error while ordering a book.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
