package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToUpdateLibraryCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToUpdateLibraryCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            String libraryId = req.getParameter("libraryId");
            Library library = new Library();
            if (libraryId != null) {
                library = libraryService.showById(libraryId).orElse(new Library("-", "-"));
            }
            req.getSession().setAttribute("updateLibraryId", library.getLibraryId());
            req.getSession().setAttribute("library", library);
            req.getRequestDispatcher(PathJsp.UPDATE_LIBRARY_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            logger.error("An error occurred while preparing the library for updating.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
