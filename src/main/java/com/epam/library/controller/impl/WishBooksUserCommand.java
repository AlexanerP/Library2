package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.entity.dto.WishBookDto;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.WishBookDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WishBooksUserCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.WISH_BOOKS_USER_PAGE);
            logger.info("Getting favorite books.");
            WishBookDtoService wishBookDtoService = ServiceFactory.getInstance().getWishBookDtoService();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            List<WishBookDto> books = new ArrayList<>();
            if (user != null) {
                books = wishBookDtoService.showBooksUser(user.getUserId() + "");
            }
            req.setAttribute("books", books);
            req.getRequestDispatcher(PathJsp.WISH_BOOK_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            logger.error("An error occured while retrieving favorite books." , e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
