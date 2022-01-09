package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
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
import java.util.ArrayList;
import java.util.List;

public class ActionAuthorCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionAuthorCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showAll();
            String authorId = req.getParameter("authorId");
            String name = req.getParameter("name");
            String allAuthor = req.getParameter("all");
            List<Author> authors = new ArrayList<>();
            if(name != null || authorId != null || allAuthor != null) {
                if (name != null) {
                    authors.add(authorService.showAuthorByName(name).orElse(new Author()));
                } else if (authorId != null) {
                    authors.add(authorService.showAuthorById(authorId).orElse(new Author()));
                } else if (allAuthor != null) {
                    authors = authorService.showAllAuthors();
                }
                req.setAttribute("libraries", libraries);
                req.setAttribute("authors", authors);
                req.setAttribute("authorsSize", authors.size());
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error when working in the authors directory.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
