package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionUserCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            UserService userService = ServiceFactory.getInstance().getUserService();
            String userId = req.getParameter("userId");
            String status = req.getParameter("status");
            String message = "User status not updated";
            if (userId != null && userId != "" && status != null && status != "") {
                userService.updateStatus(userId, status);
                message = "User status update";
            }
            resp.sendRedirect("Controller?command=GoToMessagePage&message=" + message);
//            req.getRequestDispatcher(PathFile.USER_CATALOG_PAGE).include(req, resp);
        }catch (ServiceException e) {

        }
    }
}
