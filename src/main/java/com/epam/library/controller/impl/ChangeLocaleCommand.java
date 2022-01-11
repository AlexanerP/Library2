package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = (String) req.getSession().getAttribute("url");
        if (url != "" || url != null) {
            resp.sendRedirect(url);
        } else {
            resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MAIN_PAGE);
        }
    }
}
