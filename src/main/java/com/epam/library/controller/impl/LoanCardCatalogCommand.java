package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
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

public class LoanCardCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(LoanCardCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.LOAN_CARD_CATALOG);
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            LoanCardDtoService loanCardService = ServiceFactory.getInstance().getLoanCardDtoService();
            List<LoanCardDto> loanCards = new ArrayList<>();
            List<Library> libraries = libraryService.showAll();
            String userId = req.getParameter("userId");
            String bookId = req.getParameter("bookId");
            String city = req.getParameter("city");
            String all = req.getParameter("getAll");
            String cardId = req.getParameter("cardId");
            String status = req. getParameter("status");
            if (userId != null && userId != "") {
                loanCards = loanCardService.showCardsByUser(userId);
            } else if (bookId != null && bookId != "") {
                loanCards = loanCardService.showCardsByBook(bookId);
            } else if (city != null && status == null) {
                loanCards = loanCardService.showCardsByCity(city);
            } else if (all != null && all != "") {
                loanCards = loanCardService.showAll();
            } else if (cardId != null) {
                loanCards.add(loanCardService.showCardsById(cardId).orElse(new LoanCardDto()));
            } else if (status != null && city == null) {
                loanCards = loanCardService.showCardsByStatus(status);
            } else if (status != null && city != null) {
                loanCards = loanCardService.showCardsByCityAndStatus(city, status);
            }
            req.setAttribute("libraries", libraries);
            req.setAttribute("loanCards", loanCards);
            req.setAttribute("loanCardsSize", loanCards.size());
            req.getRequestDispatcher(PathJsp.LOAN_CARD_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error working with the loan cards directory.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
