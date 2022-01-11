package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
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

public class CatalogBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CatalogBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.CATALOG_BOOK);
            BookDtoService bookService = ServiceFactory.getInstance().getBookDtoService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showAll();

            String bookId = req.getParameter("bookId");
            String title = req.getParameter("title");
            String isbn = req.getParameter("isbn");
            String author = req.getParameter("author");
            String genre = req.getParameter("genre");
            String all = req.getParameter("all");
            String city = req.getParameter("city");

            List<BookDto> books = new ArrayList<>();

            if(bookId == null && title == null && isbn == null && author == null
                    && genre == null && all == null && city == null) {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            } else {
                if (bookId != null) {
                    books.add(bookService.showBookById(bookId).orElse(new BookDto("-", "-")));
                }else if (title != "" && title != null || isbn != "" && isbn != null
                        || author != "" && author != null || genre != "" && genre != null) {
                    books = bookService.showBookByParameter(title, isbn, genre, author);
                } else if (all != null){
                    books = bookService.showCatalog();
                } else if (city != null) {
                    books = bookService.showBookByCity(city);
                    System.out.println("CitySize - " + books.size());
                }
                req.setAttribute("libraries", libraries);
                req.setAttribute("books", books);
                req.setAttribute("booksSize", books.size());
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error in controllers book directory." , e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
