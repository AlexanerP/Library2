package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToAdminPageCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToAdminPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
            logger.info("Go to the admin page.");
//            HttpSession session = req.getSession();
//            OrderService orderService = ServiceFactory.getInstance().getOrderService();
//            int countOrders = orderService.getCountOrders();
//            session.setAttribute("countOrders", countOrders);

            resp.sendRedirect("Controller?command=AdminPage");
//
//        }catch (ServiceException e) {
//            logger.error("Error getting admin page.", e);
//        }
    }
}
