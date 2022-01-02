package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
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
            logger.info("Search book by parameters.");
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            String isbn = req.getParameter("isbn");
            String title = req.getParameter("title");
            String genre = req.getParameter("genre");
            String author = req.getParameter("author");
            List<BookDto> booksDTO = new ArrayList<>();
System.out.println("isbn/" + isbn + ". title/" + title + ". genre/" + genre + ". author/" + author);
            if (isbn == "" && title == "" && genre == "" && author == "") {
                req.getRequestDispatcher("/Controller?command=GoToMainPage").forward(req, resp);
            } else {
                booksDTO = bookDtoService.showBookByParameter(title, isbn, genre, author);
                LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
                List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
                req.setAttribute("libraries", libraries);
                req.setAttribute("booksDTOSize", booksDTO.size());
                req.setAttribute("booksDTO", booksDTO);
                req.getRequestDispatcher(PathFile.MAIN_PAGE).forward(req, resp);
            }
//            req.getRequestDispatcher("WEB-INF/pages/mainPage.jsp").forward(req, resp);
        }catch (ServiceException e) {
            logger.error("An error occured while searching the book by parameters. ", e);
        }
    }
}
