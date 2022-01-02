package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateLibraryCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            String libraryId = req.getParameter("libraryId");
            String city = req.getParameter("city");
            String street = req.getParameter("street");
System.out.println("libraryId/" + libraryId + ". city/" + city + ". street" + street);
            libraryService.update(libraryId, city, street);
        }catch (ServiceException e) {

        }
    }
}
