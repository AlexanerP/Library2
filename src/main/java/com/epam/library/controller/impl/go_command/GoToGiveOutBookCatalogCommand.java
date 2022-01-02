package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToGiveOutBookCatalogCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
            req.setAttribute("libraries", libraries);
            req.getRequestDispatcher(PathFile.GIVE_OUT_BOOK_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
