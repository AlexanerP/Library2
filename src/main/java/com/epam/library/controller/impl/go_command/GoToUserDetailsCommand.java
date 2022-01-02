package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoToUserDetailsCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            UserService userService = ServiceFactory.getInstance().getUserService();
            String userId = req.getParameter("userId");
            List<User> users = new ArrayList<>();
            if (userId != null && userId != "") {
                users.add(userService.showUserById(userId).get());
            }
System.out.println("userId/" + userId);
            req.setAttribute("users", users);
            req.getRequestDispatcher(PathFile.USER_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {

        }
    }
}
