package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
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
import java.util.Optional;

public class GoToBookDetailsCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToBookDetailsCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Book details.");
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            String bookId = req.getParameter("bookId");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" + bookId);
//            BookDto book;
            if (bookId != null && bookId != "") {
//                Optional<BookDto> optional = bookDtoService.showBook(Long.parseLong(bookId));
//                book = optional.get();
                List<BookDto> book = new ArrayList<>();
                book.add(bookDtoService.showBookById(bookId).get());
                req.setAttribute("books", book);
                req.getRequestDispatcher(PathFile.BOOK_CATALOG_PAGE).forward(req, resp);
//                resp.sendRedirect(PathFile.BOOK_DETAILS);
             }
        }catch (ServiceException e){
            logger.error("Error while executing GoToBookDetailsCommand.", e);
            resp.sendRedirect(PathFile.ERROR_PAGE);
        }
    }
}
