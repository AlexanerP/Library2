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
import java.util.Optional;

public class UserCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UserCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String showAll = req.getParameter("allUser");
            String userRole = req.getParameter("user_role");
            String userStatus = req.getParameter("status");
            String userId = req.getParameter("userId");
            String email = req.getParameter("email");

            UserService userService = ServiceFactory.getInstance().getUserService();
            List<User> users = new ArrayList<>();
            if (userRole !="" && userRole != null) {
               users = userService.showUserByRole(UserRole.ADMIN.name());
               users.addAll(userService.showUserByRole(UserRole.MANAGER.name()));
            } else if (showAll != "" && showAll != null) {
                users = userService.getUsers();
            }else if (userId != "" && userId != null) {
                Optional<User> userOptional = Optional.of(userService.showUserById(userId).orElse(new User("-", "-")));
                users.add(userOptional.get());
            }else if(email != "" && email != null) {
                users.addAll(userService.showUserByEmail(email));
            } else if (userStatus !="" && userStatus != null) {
                users.addAll(userService.showUserByStatus(userStatus));
            }
            req.setAttribute("users", users);
            req.setAttribute("userSize", users.size());
            req.getRequestDispatcher(PathJsp.USER_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while working in the user directory.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
