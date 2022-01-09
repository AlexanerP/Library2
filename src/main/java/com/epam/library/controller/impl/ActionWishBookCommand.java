package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.WishBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ActionWishBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionWishBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            WishBookService wishBookService = ServiceFactory.getInstance().getWishBookService();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            String bookId = req.getParameter("bookId");
            String actionAdd = req.getParameter("add");
            String wishBookId = req.getParameter("wish_book_id");
            if(bookId != null && actionAdd != null) {
                boolean result = wishBookService.add(user.getUserId() + "", bookId);
                if (result) {
                    String successfulMessage = "Successful operation";
                    session.setAttribute("successfulMessage", successfulMessage);
                } else {
                    String negativeMessage = "Operation failed";
                    session.setAttribute("negativeMessage", negativeMessage);
                }
                resp.sendRedirect("Controller?command=GoToMessagePage");
            } else if (wishBookId != null) {
                boolean result = wishBookService.delete(wishBookId);
                if (result) {
                    resp.sendRedirect("Controller?command=GoToWishBooksUserPage");
                } else {
                    String negativeMessage = "Operation failed";
                    session.setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=GoToMessagePage");
                }
            } else {
                resp.sendRedirect("Controller?command=GoToWishBooksUserPage");
            }
        }catch (ServiceException e) {
            logger.error("Error during action with selected books, when deleting or adding.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
