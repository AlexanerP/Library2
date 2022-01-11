package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.entity.LoanCardStatus;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ReturnBookCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.RETURN_BOOK_CATALOG);
            LoanCardDtoService loanCardDtoService = ServiceFactory.getInstance().getLoanCardDtoService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<LoanCardDto> loanCards = new ArrayList<>();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());

            String loanCardId = req.getParameter("loanCardId");
            String userId = req.getParameter("userId");
            String bookId = req.getParameter("bookId");
            String library = req.getParameter("library");
            String allOpenLoanCard = req.getParameter("getAll");

            if (loanCardId != null) {
                loanCards.add(loanCardDtoService.showCardsById(loanCardId).orElse(new LoanCardDto()));
            } else if (userId != null) {
                loanCards = loanCardDtoService.showCardsByUser(userId);
            }else if (bookId != null) {
                loanCards = loanCardDtoService.showCardsByBook(bookId);
            } else if (library != null) {
                loanCards = loanCardDtoService.showCardsByCityAndStatus(library, LoanCardStatus.OPEN.name());
            } else if (allOpenLoanCard != null) {
                loanCards = loanCardDtoService.showCardsByStatus(LoanCardStatus.OPEN.name());
            }
            req.setAttribute("library", libraries);
            req.setAttribute("loanCards", loanCards);
            req.setAttribute("loanCardSize", loanCards.size());
            req.getRequestDispatcher(PathJsp.RETURN_BOOK_PAGE).forward(req, resp);

        } catch (ServiceException e) {
            logger.error("Book return directory error.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
