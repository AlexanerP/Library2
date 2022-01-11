package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
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
import java.util.ArrayList;
import java.util.List;

public class LibraryCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(LibraryCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.LIBRARY_CATALOG);
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            String libraryId = req.getParameter("libraryId");
            String city = req.getParameter("city");
            String status = req.getParameter("status");
            String getAll = req.getParameter("getAll");
            List<Library> libraries = new ArrayList<>();
            if (libraryId != null) {
                libraries.add(libraryService.showById(libraryId).get());
            } else if (city != null) {
                libraries.add(libraryService.showByCity(city).get());
            } else if (status != null) {
                libraries = libraryService.showByStatus(status);
            } else if (getAll != null) {
                libraries = libraryService.showAll();
            }
            req.setAttribute("libraries", libraries);
            req.getRequestDispatcher(PathJsp.LIBRARY_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while working with the library catalog.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
