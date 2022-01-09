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

public class UpdateUserPasswordCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserPasswordCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String email = req.getParameter("email");
            String oldPassword = req.getParameter("old_password");
            String newPassword = req.getParameter("new_password");
            if (email != "" && oldPassword != "" && newPassword != "") {
                boolean flag = userService.updatePassword(newPassword, email, oldPassword);
                if (flag) {
                    String message = "Operation completed";
                    session.setAttribute("successfulMessage", message);
                    resp.sendRedirect("Controller?command=GoToMessagePage");
                } else {
                    String negativeMessage = "Operation failed";
                    session.setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=GoToMessagePage");
                }
            } else {
                req.getRequestDispatcher(PathJsp.UPDATE_USER_PAGE).forward(req, resp);
            }

        }catch (ServiceException e) {
            logger.error("Error during password update.", e);
            resp.sendRedirect("error.jsp");
        }
    }
}
