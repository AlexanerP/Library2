package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
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

public class ActionUserCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String userId = req.getParameter("userId");
            String status = req.getParameter("status");
            if (userId != null && status != null) {
                boolean flag = userService.updateStatus(userId, status);
                if (flag) {
                    String message = "Operation completed";
                    session.setAttribute("successfulMessage", message);
                    resp.sendRedirect("Controller?command=GoToMessagePage");
                }else {
                    String negativeMessage = "Operation failed";
                    session.setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=GoToMessagePage");
                }
            }else {
                req.getRequestDispatcher(PathJsp.USER_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error when changing user status.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
