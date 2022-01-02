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


import java.util.List;

public class GoToCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Go to catalog. Getting data to view on the first page.");
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            int page = 1;
            int limit = 20;
            List<BookDto> bookList = bookDtoService.showByPage(page, limit);
            req.setAttribute("bookList", bookList);
            req.setAttribute("pageCatalog", page);
            req.getRequestDispatcher(PathFile.BOOK_CATALOG_BY_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            logger.error("An error occurred while getting data for viewing on the first page. No data received", e);
            //error page
        }
    }
}
