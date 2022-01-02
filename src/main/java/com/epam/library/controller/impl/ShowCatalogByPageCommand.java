package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.impl.BookDtoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

public class ShowCatalogByPageCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ShowCatalogByPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Show catalog by page.");
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            String next = req.getParameter("next");
            String back = req.getParameter("back");
            String pageLine = req.getParameter("jumpPage");
            List<BookDto> bookResult;
            Integer page = 0;
            int limit = 20;
//            if (pageLine != null) {
//                if (pageLine.length() >= 1) {
//                    page = Integer.parseInt(pageLine);
//                } else {
//                    page = 1;
//                }
//            } else {
//                page = Integer.parseInt(req.getParameter("pageCatalog"));
//            }

            if (next != null) {
                page++;
            } else if (back != null) {
                page--;
            } else if (pageLine != null) {
//               if (pageLine.length() >= 1) {
                    page = Integer.parseInt(pageLine);
//                } else {
//                    page = 1;
//                }
            } else {
                page = 1;
            }
            bookResult = bookDtoService.showByPage(page, limit);
            req.setAttribute("pageCatalog", page);
            req.setAttribute("bookList", bookResult);

            req.getRequestDispatcher(PathFile.BOOK_CATALOG_BY_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("An error occurred while browsing the book catalog.", e);
            resp.sendRedirect("error.jsp");
        }
    }
}