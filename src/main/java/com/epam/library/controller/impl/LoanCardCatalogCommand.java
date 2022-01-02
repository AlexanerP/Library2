package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.LibraryService;
import com.epam.library.service.LoanCardService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoanCardCatalogCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            List<LoanCardDto> loanCards = new ArrayList<>();
            List<Library> libraries = libraryService.showAll();
            String userId = req.getParameter("userId");
            String bookId = req.getParameter("bookId");
            String city = req.getParameter("city");
            String all = req.getParameter("getAll");
 System.out.println("userId/" + userId + ". bookId/" + bookId + ". city/" + city + ". all/" + all);
            if (userId != null && userId != "") {
                System.out.println("userId");
                loanCards = loanCardService.showCardsByUser(1l);
            } else if (bookId != null && bookId != "") {
                System.out.println("bookId");
                loanCards = loanCardService.showCardsByBook("2");
            } else if (city != null && city != "") {
                System.out.println("city");
                loanCards = loanCardService.showCardsByCity(city);
            } else if (all != null && all != "") {
                System.out.println("all");
                loanCards = loanCardService.showAll();
            }

            req.setAttribute("libraries", libraries);
            req.setAttribute("loanCards", loanCards);
            req.setAttribute("loanCardsSize", loanCards.size());
            req.getRequestDispatcher(PathFile.LOAN_CARD_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {

        }
    }
}
