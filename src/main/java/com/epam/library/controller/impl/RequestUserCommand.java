package com.epam.library.controller.impl;

import com.epam.library.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RequestUserCommand");
        req.getRequestDispatcher("WEB-INF/pages/orderUserPage.jsp").forward(req, resp);
    }
}
