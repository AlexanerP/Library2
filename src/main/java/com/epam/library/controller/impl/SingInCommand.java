package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Optional;

public class SingInCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(SingInCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = new UserServiceImpl();
            HttpSession session = req.getSession();
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            System.out.println("email" + email);
            System.out.println("password" + password);
            if (email != "" && password != "") {
                Optional<User> optionalUser = userService.verification(email, password);
                if (optionalUser.isPresent()) {
                    User user = new User();
                    user.setUserId(optionalUser.get().getUserId());
                    user.setRole(optionalUser.get().getRole());
                    user.setSecondName(optionalUser.get().getSecondName());
                    user.setLastName(optionalUser.get().getLastName());
                    user.setStatus(optionalUser.get().getStatus());
                    session.setAttribute("user", user);
//                    req.getRequestDispatcher("WEB-INF/pages/homePage.jsp").forward(req, resp);
                    resp.sendRedirect("Controller?command=GoToHome");
                } else {
                    req.setAttribute("message", "User is not found.");
                    resp.sendRedirect(PathFile.MESSAGE_PAGE);
//                    req.getRequestDispatcher(PathFile.INDEX_PAGE).forward(req, resp);
                }
            } else {
                resp.sendRedirect(PathFile.INDEX_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Verification error.", e);
        }
    }
}