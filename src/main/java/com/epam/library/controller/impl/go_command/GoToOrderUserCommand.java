package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToOrderUserCommand implements Command {

    private final static Logger logger = LoggerFactory.getLogger(GoToOrderUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Getting orders for user.");
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            UserService userService = ServiceFactory.getInstance().getUserService();
            String secondName = "Test";    //Optional.map
            String lastName = "Test";    //Optional.map
            String userId = req.getParameter("userId");
            List<OrderDto> orders = orderDtoService.showOrdersUser(Long.parseLong(userId));

            req.setAttribute("secondName", secondName);
            req.setAttribute("lastName", lastName);
            req.setAttribute("orders", orders);
            req.getRequestDispatcher(PathFile.ORDER_USER_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.warn("", e);
            resp.sendRedirect(PathFile.MESSAGE_PAGE);
        }
    }
}
