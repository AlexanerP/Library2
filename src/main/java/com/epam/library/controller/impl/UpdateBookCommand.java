package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
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
            String bookId = req.getParameter("bookId");
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

            boolean flagResult;
            String message = "";
            System.out.println("title/" + title + ". isbn/" + isbn + ". cityLibrary/" + cityLibrary
             + ". author/" + author + ". genre/" + genre + ". publisher/" + publisher + ". year/" + year
            + ". shelf/" + shelf + ". quantity/" + quantity + ". description/" + description);
            if (title != "" || isbn != "" || cityLibrary != "" || author != "" || genre != "" || publisher != ""
                || year != "" || shelf != "" || quantity != "" || description != "") {
                if (author == "" && genre == "") {
                    Book book = new Book();

                    book.setTitle(title);
                    book.setIsbn(isbn);
                    book.setPublisher(publisher);
                    book.setYear(year);
                    book.setShelf(shelf);
                    book.setDescription(description);

                    flagResult = bookService.update(bookId, book, quantity, cityLibrary);
                } else {
                    BookDto bookDto = new BookDto();

                    bookDto.setTitle(title);
                    bookDto.setIsbn(isbn);
                    bookDto.setCityLibrary(cityLibrary);
                    bookDto.setPublisher(publisher);
                    bookDto.setYear(year);
                    bookDto.setShelf(shelf);
                    bookDto.setDescription(description);

                    flagResult = bookDtoService.update(bookId, bookDto, author, genre, quantity);
                }
                if (flagResult) {
                    message = "Book saved";
                }else {
                    message = "The book has not been saved. Perhaps the text is too long.";
                }
                resp.sendRedirect("Controller?command=GoToMessagePage&message=" + message);
            } else {
                req.getRequestDispatcher(PathFile.UPDATE_BOOK_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("When updating the book, the error.", e);
        }
    }
}
