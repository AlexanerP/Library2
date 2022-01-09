package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Genre;
import com.epam.library.entity.Library;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToStatisticGenreCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToStatisticGenreCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showAll();
            String genreId = req.getParameter("genreId");
            if (genreId != null) {
                Genre genre = genreService.showGenreById(genreId).orElse(new Genre("-"));
                long countBook = genreService.getCountBooksByGenres(genre.getCategory());

                req.setAttribute("libraries", libraries);
                req.setAttribute("statGenre", genre);
                req.setAttribute("genreCountBooks", countBook);
            }
            req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while viewing author statistics.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
