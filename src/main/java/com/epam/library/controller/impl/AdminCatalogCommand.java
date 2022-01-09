package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(AdminCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            UserService userService = ServiceFactory.getInstance().getUserService();
            String userIdFind = req.getParameter("userIdFind");
            String email = req.getParameter("email");
            String admins = req.getParameter("allAdmins");
            List<User> users = new ArrayList<>();
            if (userIdFind != null && userIdFind != "") {
               users.add(userService.showUserById(userIdFind).get());
            } else if (email != null && email != "") {
                users.addAll(userService.showUserByEmail(email));
            } else if (admins != null && admins != "") {
                users.addAll(userService.showUserByRole(UserRole.ADMIN.name()));
                users.addAll(userService.showUserByRole(UserRole.MANAGER.name()));
            }
            req.setAttribute("users", users);
            req.getRequestDispatcher(PathJsp.MANAGER_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while searching for administrators.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
