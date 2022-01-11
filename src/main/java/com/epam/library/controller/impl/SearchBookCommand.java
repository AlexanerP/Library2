package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(SearchBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.SEARCH_BOOKS);
            logger.info("Search book by parameters.");
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            BookService bookService = ServiceFactory.getInstance().getBookService();

            long countBooks = bookService.getCountBooks();
            int countAuthors = authorService.getCountAuthors();
            long countGenres = genreService.getCountGenres();

            String isbn = req.getParameter("isbn");
            String title = req.getParameter("title");
            String genre = req.getParameter("genre");
            String author = req.getParameter("author");
            List<BookDto> booksDTO = new ArrayList<>();
            if (isbn == "" && title == "" && genre == "" && author == "") {
                req.getRequestDispatcher("/Controller?command=" + CommandType.GO_TO_MAIN_PAGE).forward(req, resp);
            } else {
                booksDTO = bookDtoService.showBookByParameter(title, isbn, genre, author);
                LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
                List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
                req.setAttribute("libraries", libraries);
                req.setAttribute("booksDTOSize", booksDTO.size());
                req.setAttribute("booksDTO", booksDTO);
                req.setAttribute("countBooks", countBooks);
                req.setAttribute("countAuthors", countAuthors);
                req.setAttribute("countGenres", countGenres);

                req.getRequestDispatcher(PathJsp.MAIN_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("An error occured while searching the book by parameters. ", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
