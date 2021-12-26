package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToUpdateUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GoToUpdateUserCommand");
        req.getRequestDispatcher("WEB-INF/pages/updateUserPage.jsp").forward(req,resp);
    }
}
