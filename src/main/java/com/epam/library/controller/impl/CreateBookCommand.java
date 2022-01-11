package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CreateBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.CREATE_BOOK);
            String title = req.getParameter("title");
            String isbn = req.getParameter("isbn");
            String publisher = req.getParameter("publisher");
            String year = req.getParameter("year");
            String count = req.getParameter("count");
            String city = req.getParameter("city");
            String shelf = req.getParameter("shelf");
            String author = req.getParameter("author");
            String category = req.getParameter("category");
            String description = req.getParameter("description");

            if (title != "" && title != null && isbn != "" && isbn != null && publisher != "" && publisher != null
                    && year != "" && year != null && count != "" && count != null && shelf != "" && shelf != null
            && author != "" && author != null && category != "" && category != null) {
                BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
                boolean resultOperation = bookDtoService.create(title, isbn, publisher, year, count, city, shelf,
                        author, category,description);
                if (resultOperation) {
                    String successfulMessage = "Operation successful";
                    req.getSession().setAttribute("successfulMessage", successfulMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    String negativeMessage = "Operation failed";
                    req.getSession().setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.ADD_BOOK_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error while creating book.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
