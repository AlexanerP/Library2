package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToLoanCardCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToLoanCardCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
            req.setAttribute("libraries", libraries);
            req.getRequestDispatcher(PathFile.LOAN_CARD_CATALOG_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            logger.error("Error getting libraries for the loan cards directory.", e);
            resp.sendRedirect(PathFile.ERROR_PAGE);
        }
    }
}
