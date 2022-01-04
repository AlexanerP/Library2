package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
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

public class GoToOrderCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToOrderCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Library> libraries;
            LibraryServiceImpl libraryService = new LibraryServiceImpl();
            libraries = libraryService.showAll();
            req.setAttribute("libraries", libraries);
            req.getRequestDispatcher(PathFile.ORDERS_CATALOG_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
