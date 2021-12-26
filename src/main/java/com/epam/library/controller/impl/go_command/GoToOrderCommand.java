package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.entity.Library;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.impl.BookDtoServiceImpl;
import com.epam.library.service.impl.LibraryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToOrderCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToOrderCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Preparing to order a book.");
            HttpSession session = req.getSession();
            BookDtoService bookDtoService = new BookDtoServiceImpl();
            LibraryServiceImpl libraryService = new LibraryServiceImpl();
            List<Library> cities = libraryService.getLibraries();
            BookDto bookDTO;
            String bookId = req.getParameter("bookId");
            if (bookId != "") {
                bookDTO = bookDtoService.showBook(Long.parseLong(bookId));
                req.setAttribute("cities", cities);
                req.setAttribute("book", bookDTO);
                session.setAttribute("bookId", bookDTO.getBookDtoId());
                req.getRequestDispatcher(PathFile.ORDER_PAGE).forward(req, resp);
            } else {
                resp.sendRedirect(PathFile.MESSAGE_PAGE);
            }

        } catch (ServiceException e) {
            logger.error("An error occurred while preparing to order the book.", e);

        }
    }
}
