package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.*;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToMainPageCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Go to main page command.");

            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            BookService bookService = ServiceFactory.getInstance().getBookService();

            long countBooks = bookService.getCountBooks();
            int countAuthors = authorService.getCountAuthors();
            long countGenres = genreService.getCountGenres();

            req.setAttribute("countBooks", countBooks);
            req.setAttribute("countAuthors", countAuthors);
            req.setAttribute("countGenres", countGenres);
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
            req.getSession().setAttribute("libraries", libraries);
//            req.setAttribute("libraries", libraries);

            req.getRequestDispatcher(PathJsp.MAIN_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while executing the command GoTo Main Command error during command execution", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
