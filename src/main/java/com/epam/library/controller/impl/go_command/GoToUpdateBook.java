package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
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

public class GoToUpdateBook implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToUpdateBook.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            String bookId = req.getParameter("bookId");
            Optional<BookDto> optionalBook = bookDtoService.showBookById(bookId);

            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
            req.setAttribute("libraries", libraries);
            req.setAttribute("book", optionalBook.get());
            req.getRequestDispatcher(PathFile.UPDATE_BOOK_PAGE).forward(req, resp);

        }catch (ServiceException e) {
            logger.error("Book for update not received.", e);
            e.printStackTrace();
        }
    }
}
