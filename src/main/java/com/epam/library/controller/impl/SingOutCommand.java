package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SingOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        System.out.println("INVALID");
        resp.sendRedirect("Controller?command=GoToMainPage");
//        req.getRequestDispatcher(PathFile.INDEX_PAGE).forward(req, resp);
//        req.getRequestDispatcher(PathFile.INDEX_PAGE).include(req, resp);
    }
}
