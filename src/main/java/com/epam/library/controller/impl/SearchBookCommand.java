package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.impl.BookDtoServiceImpl;
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
            logger.info("Search book by parameter.");
            BookDtoService bookDtoService = new BookDtoServiceImpl();
            String isbn = req.getParameter("isbn");
            String title = req.getParameter("title");
            String genre = req.getParameter("genre");
            List<BookDto> booksDTO = new ArrayList<>();

            if (isbn == "" && title == "" && genre == "") {
                req.getRequestDispatcher("WEB-INF/pages/mainPage.jsp").forward(req, resp);
            } else {
                    booksDTO = bookDtoService.showBookByParameter(title, isbn, genre);
            }
            req.setAttribute("booksDTO", booksDTO);
            req.getRequestDispatcher("WEB-INF/pages/mainPage.jsp").forward(req, resp);
//            req.getRequestDispatcher("WEB-INF/pages/mainPage.jsp").forward(req, resp);
        }catch (ServiceException e) {
            logger.error("An error occured while searching the book by parameters. ", e);
        }
    }
}
