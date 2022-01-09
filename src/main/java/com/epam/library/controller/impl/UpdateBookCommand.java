package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Book;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.BookService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            BookService bookService = ServiceFactory.getInstance().getBookService();
            Long bookId = (Long) req.getSession().getAttribute("updateBookId");
            String title = req.getParameter("title");
            String isbn = req.getParameter("isbn");
            String cityLibrary = req.getParameter("cityLibrary");
            String author = req.getParameter("author");
            String genre = req.getParameter("genre");
            String publisher = req.getParameter("publisher");
            String year = req.getParameter("year");
            String shelf = req.getParameter("shelf");
            String quantity = req.getParameter("quantity");
            String description = req.getParameter("description");

            boolean resultOperation;


           if (bookId != null) {
               if (title != "" && title != null || isbn != "" && isbn != null
                       || author != "" && author != null || genre != "" && genre != null
                       || publisher != "" && publisher != null
                       || year != "" && year != null || shelf != "" && shelf !=null
                       || quantity != "" && quantity != null || description != "" && description != null) {
                   if (author == "" && genre == "") {
                       Book book = new Book();

                       book.setTitle(title);
                       book.setIsbn(isbn);
                       book.setPublisher(publisher);
                       book.setYear(year);
                       book.setShelf(shelf);
                       book.setDescription(description);

                       req.getSession().removeAttribute("updateBookId");
                       resultOperation = bookService.update(bookId + "", book, quantity, cityLibrary);
                   } else {
                       BookDto bookDto = new BookDto();

                       bookDto.setTitle(title);
                       bookDto.setIsbn(isbn);
                       bookDto.setCityLibrary(cityLibrary);
                       bookDto.setPublisher(publisher);
                       bookDto.setYear(year);
                       bookDto.setShelf(shelf);
                       bookDto.setDescription(description);

                       req.getSession().removeAttribute("updateBookId");
                       resultOperation = bookDtoService.update(bookId + "", bookDto, author, genre, quantity);
                   }
                   if (resultOperation) {
                       String successfulMessage = "Operation successful";
                       req.getSession().setAttribute("successfulMessage", successfulMessage);
                       resp.sendRedirect("Controller?command=GoToMessagePage");
                   } else {
                       String negativeMessage = "Operation failed";
                       req.getSession().setAttribute("negativeMessage", negativeMessage);
                       resp.sendRedirect("Controller?command=GoToMessagePage");
                   }
               } else {
                   String negativeMessage = "Operation failed";
                   req.getSession().setAttribute("negativeMessage", negativeMessage);
                   resp.sendRedirect("Controller?command=GoToMessagePage");
               }
           } else {
               req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
           }
        }catch (ServiceException e) {
            logger.error("When updating the book, the error.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
