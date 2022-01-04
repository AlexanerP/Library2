package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.entity.User;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.impl.OrderDtoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
System.out.println("OrderCommand /");
            HttpSession session = req.getSession();
            String bookId = req.getParameter("bookId");
System.out.println("bookId /" + bookId);
            User user = (User) session.getAttribute("user");
            String city = req.getParameter("city");
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            System.out.println("BookID/ " + bookId);
            if (user != null && bookId != null && city !="") {
                if (orderService.create("Long.parseLong(bookId", "ser.getUserId()", city, "")) {
                    resp.sendRedirect("Controller?command=GoToMessagePage&message=" + "Order success.");
                }
            } else {
                resp.sendRedirect("Controller?command=GoToMessagePage&message=" + "Order error.");
            }
        }catch (ServiceException e) {
            logger.error("Error while placing an order.", e);
            resp.sendRedirect("error.jsp");
        }
    }
}
