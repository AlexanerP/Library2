package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
import com.epam.library.service.ServiceException;
import com.epam.library.service.impl.LibraryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAddBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToAddBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            LibraryServiceImpl libraryService = new LibraryServiceImpl();
//            List<Library> libraries = libraryService.showAll();
//            req.setAttribute("libraries", libraries);
//            req.getRequestDispatcher(PathJsp.ADD_BOOK_PAGE).forward(req, resp);
//        } catch (ServiceException e) {
//            logger.error("Error getting library to add book.", e);
//            resp.sendRedirect(PathJsp.ERROR_PAGE);
//        }
    }
}
