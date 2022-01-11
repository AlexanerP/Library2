package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
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
import java.util.ArrayList;
import java.util.List;

public class ActionGenreCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionGenreCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.ACTION_GENRE);
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showAll();
            String genreId = req.getParameter("genreId");
            String name = req.getParameter("name");
            String allGenre = req.getParameter("all");
            List<Genre> genres = new ArrayList<>();
            if(name != null || genreId != null || allGenre != null) {
                if (name != null) {
                    genres.add(genreService.showGenreByCategory(name).orElse(new Genre()));
                } else if (genreId != null) {
                    genres.add(genreService.showGenreById(genreId).orElse(new Genre("-")));
                } else if (allGenre != null) {
                    genres = genreService.getGenres();
                }
                req.setAttribute("libraries", libraries);
                req.setAttribute("genre", genres);
                req.setAttribute("genreSize", genres.size());
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error when working in the genres directory.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
