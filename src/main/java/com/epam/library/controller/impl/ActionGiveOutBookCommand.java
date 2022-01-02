package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.service.BookService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionGiveOutBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionGiveOutBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            String bookId = req.getParameter("bookId");
            String bookId = "1";
            // userId
            System.out.println("bookId - " + bookId);

            BookService bookService = ServiceFactory.getInstance().getBookService();
            bookService.updateBorrow(bookId);
        }catch (ServiceException e) {
            logger.error("Error while issuing a book.");
        }
    }
}
