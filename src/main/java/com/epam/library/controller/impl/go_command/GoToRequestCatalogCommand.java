package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
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

public class GoToRequestCatalogCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GoToRequestCatalogCommand");
        List<Library> cities = null;
        List<Status> statuses = new ArrayList<>();
        statuses.add(new Status("Opened"));
        statuses.add(new Status("Closed"));
        statuses.add(new Status("DELETE"));
        try {
            LibraryServiceImpl libraryService = new LibraryServiceImpl();
            cities = libraryService.getLibraries();
            System.out.println(cities);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("cities", cities);
        req.setAttribute("statuses", statuses);
        req.getRequestDispatcher("WEB-INF/pages/requestCatalogPage.jsp").forward(req, resp);
    }
}
