package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.WishBookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ActionWishBookCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            WishBookService wishBookService = ServiceFactory.getInstance().getWishBookService();
            HttpSession session = req.getSession();
            String bookId = req.getParameter("bookId");
            User user = (User) session.getAttribute("user");
            wishBookService.delete(bookId, user.getUserId());
            System.out.println("bookId/" + bookId + ". userId/" + user.getUserId());
            resp.sendRedirect("Controller?command=GoToWishBooksUserPage");
        }catch (ServiceException e) {

        }
    }
}
