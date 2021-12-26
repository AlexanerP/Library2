package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.LoanCardService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.impl.LoanCardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoToLoanCardUserCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            System.out.println("GoToLoanCardCommand");
            HttpSession session = req.getSession();
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            User user = (User) session.getAttribute("user");
            String userId = req.getParameter("userId");
            List<LoanCardDto> cardsList = new ArrayList<>();
            if (userId != null && userId !="") {
                cardsList = loanCardService.showCardsByUser(Long.parseLong(userId));
            }


            req.setAttribute("loanCards", cardsList);
            req.getRequestDispatcher(PathFile.USER_LOAN_CARD).forward(req, resp);
        } catch (ServiceException e) {

        }
    }
}
