package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.service.ServiceException;
import com.epam.library.service.impl.LibraryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAddBookPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GoToAddBookPage");
        LibraryServiceImpl libraryService = new LibraryServiceImpl();
        List<Library> cities = null;
        try {
            cities = libraryService.showAll();
            System.out.println(cities);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("cities", cities);
        req.getRequestDispatcher(PathFile.ADD_BOOK_PAGE).forward(req, resp);
    }
}
