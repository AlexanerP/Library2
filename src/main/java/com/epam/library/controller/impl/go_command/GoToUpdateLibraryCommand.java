package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToUpdateLibraryCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            String libraryId = req.getParameter("libraryId");
            Library library = new Library();
            if (libraryId != null && libraryId != "") {
                library = libraryService.showById(libraryId).get();
            }
            req.setAttribute("library", library);
            req.getRequestDispatcher(PathFile.UPDATE_LIBRARY_PAGE).forward(req, resp);
        } catch (ServiceException e) {

        }
    }
}
