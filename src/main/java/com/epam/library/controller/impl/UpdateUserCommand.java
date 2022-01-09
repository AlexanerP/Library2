package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
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

public class UpdateUserCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String email = req.getParameter("email");
            String secondName = req.getParameter("second_name");
            String lastName = req.getParameter("last_name");
            User user = (User) session.getAttribute("user");
            if (email != null && email != "" || secondName != null && secondName != ""
                    || lastName != null && lastName != "") {
                boolean flag = userService.update(email, secondName, lastName, user.getUserId() + "");
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
            logger.error("An error occurred while updating the user's personal data.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
