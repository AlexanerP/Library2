package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
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
            BookDtoService bookService = ServiceFactory.getInstance().getBookDtoService();

            String bookId = req.getParameter("bookId");
            String title = req.getParameter("title");
            String isbn = req.getParameter("isbn");
            String author = req.getParameter("author");
            String genre = req.getParameter("genre");
            String all = req.getParameter("all");
            System.out.println("bookId/" + bookId + ". title/" + title + ". isbn/" + isbn+ ". author/" + author + ". genre/" + ". all/" + all);
            List<BookDto> books = new ArrayList<>();

            if(bookId == null && title == null && isbn == null && author == null && genre == null & all == null) {
                req.getRequestDispatcher(PathFile.BOOK_CATALOG_PAGE).forward(req, resp);
            } else {
                if (bookId != "" && bookId != null) {
System.out.println("bookId1");
                    books.add(bookService.showBookById(bookId).orElse(new BookDto("-", "-")));
                }else if (title == "" || isbn == "" || author == "" || genre == "") {
System.out.println("title1");
                    books = bookService.showBookByParameter(title, isbn, genre, author);
                } else if (all != ""){
System.out.println("al1 - " + all);
                    books = bookService.showCatalog();
                }
                req.setAttribute("books", books);
                req.setAttribute("booksSize", books.size());
                req.getRequestDispatcher(PathFile.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error in controllers book directory." , e);
            //  sendRedirect
        }
    }
}
