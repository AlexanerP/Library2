package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.Status;
import com.epam.library.service.ServiceException;
import com.epam.library.service.impl.LibraryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoToOrderCatalogCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Library> cities = null;
            LibraryServiceImpl libraryService = new LibraryServiceImpl();
            cities = libraryService.showAll();
            req.setAttribute("cities", cities);
            req.getRequestDispatcher(PathFile.ORDERS_CATALOG_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
