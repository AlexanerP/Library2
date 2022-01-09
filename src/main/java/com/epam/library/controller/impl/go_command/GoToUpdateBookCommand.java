package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
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
import java.util.List;
import java.util.Optional;

public class GoToUpdateBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToUpdateBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            String bookId = req.getParameter("bookId");
            Optional<BookDto> optionalBook = bookDtoService.showBookById(bookId);
            if (optionalBook.isPresent()) {
                req.getSession().setAttribute("updateBookId", optionalBook.get().getBookDtoId());
                LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
                List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
                req.setAttribute("libraries", libraries);
                req.setAttribute("book", optionalBook.get());
                req.getRequestDispatcher(PathJsp.UPDATE_BOOK_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Book for update not received.", e);
            resp.sendRedirect(PathJsp.MESSAGE_PAGE);
        }
    }
}