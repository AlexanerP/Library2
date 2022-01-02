package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateBookCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
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

            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            boolean flagResult = bookDtoService.create(title, isbn, publisher, year, count, city, shelf,
                    author, category,description);
            String message = "";
            if (flagResult) {
                message = "Book added";
                resp.sendRedirect("Controller?command=GoToMessagePage&message=" + message);
            } else {
                message = "Book not added";
                resp.sendRedirect("Controller?command=GoToMessagePage&message=" + message);
            }
        } catch (ServiceException e) {

        }
    }
}
