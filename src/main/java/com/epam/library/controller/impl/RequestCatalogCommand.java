package com.epam.library.controller.impl;

import com.epam.library.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestCatalogCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RequestsCatalog");
        req.getRequestDispatcher("WEB-INF/pages/requestCatalogPage.jsp").forward(req, resp);
    }
}
