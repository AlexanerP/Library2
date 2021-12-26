package com.epam.library.controller.impl;

import com.epam.library.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UpdateUserCommand");
        System.out.println(req.getParameter("updatePassword"));
        System.out.println(req.getParameter("updateUser"));
        resp.sendRedirect("Controller?command=GoToMessagePage&message=" + "Update yes.");
    }
}
