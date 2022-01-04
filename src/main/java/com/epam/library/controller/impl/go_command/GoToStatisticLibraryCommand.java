package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToStatisticLibraryCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToStatisticLibraryCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Getting statistics library");
            UserService userService = ServiceFactory.getInstance().getUserService();
            BookService bookService = ServiceFactory.getInstance().getBookService();

            long countUsers = userService.showCountUsers();
            long countBooks = bookService.getCountBooks();

            List<User> usersBlocked = userService.showUserByStatus(UserStatus.BLOCKED.name());
            List<User> usersActive = userService.showUserByStatus(UserStatus.ACTIVE.name());
            List<User> usersDelete = userService.showUserByStatus(UserStatus.DELETE.name());

            req.setAttribute("countUsers", countUsers);
            req.setAttribute("countBooks", countBooks);
            req.setAttribute("countUsersBlocked", usersBlocked.size());
            req.setAttribute("countUsersDelete", usersDelete.size());
            req.setAttribute("countUsersActive", usersActive.size());

            req.getRequestDispatcher(PathFile.STATISTICS_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while getting library statistics", e);
            resp.sendRedirect("error.jsp");
        }
    }
}
