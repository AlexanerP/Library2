package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ActionAdminCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            UserService userService = ServiceFactory.getInstance().getUserService();
            String userId = req.getParameter("userId");
            String role = req.getParameter("role");

            if (userId != null && userId != "" && role != null && role != "") {
                Optional<User> optionalUser = userService.showUserById(userId);
                User user = optionalUser.get();
                user.setRole(UserRole.valueOf(role.toUpperCase()));
                userService.update("user", "", "" ,"");
            }
            System.out.println("userId/" + userId + ". role/" + role);

                resp.sendRedirect("Controller?command=GoToMessagePage&message=");
//            req.getRequestDispatcher(PathFile.MANAGER_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {

        }
    }
}
