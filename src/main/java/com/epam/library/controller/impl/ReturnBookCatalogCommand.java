package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.entity.LoanCardStatus;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookCatalogCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<LoanCardDto> loanCards = new ArrayList<>();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());

            String loanCardId = req.getParameter("loanCardId");
            String userId = req.getParameter("userId");
            String bookId = req.getParameter("bookId");
            String city = req.getParameter("city");
            String all = req.getParameter("getAll");

            System.out.println("loanCardId/" + loanCardId + ". city/" + city + ". getAll/" + all + ". bookId/" + bookId + ". userId/" + userId);

            if (loanCardId != null && loanCardId != "") {
                System.out.println("orderId");
                loanCards.add(loanCardService.showCardsById(loanCardId).get());
            } else if (userId != null && userId != "") {
                loanCards = loanCardService.showCardsByUser(userId);
            }else if (bookId != null && bookId !="") {
                loanCards = loanCardService.showCardsByBook(bookId);
            } else if (city != null && city != "") {
                System.out.println("city");
                loanCards = loanCardService.showCardsByCityAndStatus(city, LoanCardStatus.OPEN);
            } else if (all != null && all != "") {
                System.out.println("all");
                loanCards = loanCardService.showCardsByStatus(LoanCardStatus.OPEN);
            }

            req.setAttribute("libraries", libraries);
            req.setAttribute("loanCards", loanCards);
            req.setAttribute("loanCardSize", loanCards.size());
            req.getRequestDispatcher(PathFile.RETURN_BOOK_PAGE).forward(req, resp);

        } catch (ServiceException e) {

        }
    }
}
