package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionLibraryCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String libraryId = req.getParameter("libraryId");
            String status = req.getParameter("status");
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
 System.out.println("libraryId/" + libraryId + ". status/" + status);
            String message = "M";
            if (libraryId != null && status != null) {
                libraryService.updateStatus(libraryId, status);
            }
            resp.sendRedirect(PathFile.MESSAGE_PAGE);
        } catch (ServiceException e) {

        }
    }
}
