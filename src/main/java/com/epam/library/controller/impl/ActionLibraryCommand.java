package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionLibraryCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionLibraryCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.ACTION_LIBRARY);
            String libraryId = req.getParameter("libraryId");
            String status = req.getParameter("status");
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            if (libraryId != null && status != null) {
                boolean resultOperation = libraryService.updateStatus(libraryId, status);
                if (resultOperation) {
                    String successfulMessage = "Operation successful";
                    req.getSession().setAttribute("successfulMessage", successfulMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    String negativeMessage = "Operation failed";
                    req.getSession().setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.LIBRARY_CATALOG_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error while changing the library status.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
