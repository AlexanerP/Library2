package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
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

public class ActionAdminCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionAdminCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.ACTION_ADMIN_COMMAND);
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String userId = req.getParameter("userId");
            String role = req.getParameter("role");

            if (userId != null && userId != "" && role != null && role != "") {
                boolean result = userService.updateRole(userId, role);
                if (result) {
                    String successfulMessage = "Successful operation";
                    session.setAttribute("successfulMessage", successfulMessage);

                } else {
                    String negativeMessage = "Operation failed";
                    session.setAttribute("negativeMessage", negativeMessage);
                }
                resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
            } else {
                req.getRequestDispatcher(PathJsp.MANAGER_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error while changing user role.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
