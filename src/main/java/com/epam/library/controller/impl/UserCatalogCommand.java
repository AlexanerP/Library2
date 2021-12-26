package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Status;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.StatusService;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.UserServiceImpl;
import com.epam.library.service.impl.UserStatusServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCatalogCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String showAll = req.getParameter("allUser");
            String showAdmin = req.getParameter("allAdmin");
            String userId = req.getParameter("userId");
            String email = req.getParameter("email");

            System.out.println("all/" + showAll+ ". showAdmin/" + showAdmin + ". id/" + userId + ". email/" + email);
            UserService userService = ServiceFactory.getInstance().getUserService();

            req.setAttribute("statusActive", UserStatus.ACTIVE.name());
            req.setAttribute("statusBlocked", UserStatus.BLOCKED.name());
            req.setAttribute("statusDelete", UserStatus.DELETE);
            List<User> users = new ArrayList<>();
            if (showAdmin !="" && showAdmin != null) {
 System.out.println("showAdmin");
               users = userService.showUserByRole(UserRole.ADMIN);
               users = userService.showUserByRole(UserRole.MANAGER);
            } else if (showAll != "" && showAll != null) {
System.out.println("showAll");
                users = userService.getUsers();
            }else if (userId != "" && userId != null) {
System.out.println("userId");
                Optional userOptional = userService.showUserById(Math.abs(Long.parseLong(userId)));
                User user = new User();
//                user.setSecondName("4");
//                String name = userOptional.flatMap(User::getSecondName).orElse("-");
                users.add(user);
            }else if(email != "" && email != null) {
System.out.println("email");
                Optional userOptional = userService.showUserByEmail(email);
                User user = new User();
                users.add(user);
            }else {
  System.out.println("USER CATALOG");
//                resp.sendRedirect(PathFile.USER_CATALOG_PAGE);
            }
//            if (showAll != null && userId != null && email != null && showAdmin != null)
    System.out.println("USER REDIRECT");
            req.setAttribute("users", users);
            req.getRequestDispatcher(PathFile.USER_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {

        }
    }
}
