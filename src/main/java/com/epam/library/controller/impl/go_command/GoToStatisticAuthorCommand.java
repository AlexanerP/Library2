package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Author;
import com.epam.library.entity.Library;
import com.epam.library.service.AuthorService;
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

public class GoToStatisticAuthorCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToStatisticAuthorCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.GO_TO_STATISTIC_AUTHOR);
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showAll();
            String authorId = req.getParameter("authorId");
            if (authorId != null) {
                Author author = authorService.showAuthorById(authorId).orElse(new Author("-"));
                long countBook = authorService.getCountBooksByAuthors(author.getName());

                req.setAttribute("libraries", libraries);
                req.setAttribute("statAuthors", author);
                req.setAttribute("authorCountBooks", countBook);
            }
            req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while viewing author statistics.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
