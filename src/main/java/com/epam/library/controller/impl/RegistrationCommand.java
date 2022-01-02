package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.service.ServiceException;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.epam.library.utill.Chiper.Cipher;
import com.epam.library.utill.UtilFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationCommand implements Command {

    private final static Logger logger = LoggerFactory.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            logger.info("Start registration.");
            UserService userService = new UserServiceImpl();
            Cipher cipher = new UtilFactory().getCipher();
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String secondName = req.getParameter("second_name");
            String lastName = req.getParameter("last_name");
            if (email != "" && password != "" && secondName != "" && lastName != ""){
                boolean flagRegistration = userService.create(email, password, secondName, lastName);
                if(flagRegistration) {
                    resp.sendRedirect("Controller?command=GoToMessagePage&message=" + "Registration was successful. " +
                            "Sign in to continue");
                } else {
                    resp.sendRedirect("Controller?command=GoToMessagePage&message=" + "Registration failed.");
                }
            } else {
                logger.error("Not all fields are filled.");
                resp.sendRedirect("Controller?command=GoToMessagePage&message=" + "Not all fields are filled.");
            }
        } catch (ServiceException e) {
            logger.error("An error occured during registration. " + e);
            resp.sendRedirect("error.jsp");
        }
    }
}
