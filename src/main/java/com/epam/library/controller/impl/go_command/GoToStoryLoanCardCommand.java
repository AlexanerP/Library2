package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.LoanCardService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.LoanCardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoToStoryLoanCardCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToStoryLoanCardCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Story loan cards of user.");
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            UserService userService = ServiceFactory.getInstance().getUserService();
            String userId = req.getParameter("userId");
            List<LoanCardDto> loanCards = new ArrayList<>();
            String secondName = "";
            String lastName = "";
            if (userId != null && userId != "") {
                loanCards = loanCardService.showCardsByUser(Long.parseLong(userId));
                secondName = "Second name Test";
                lastName = "Last name Test";
            }


            req.setAttribute("secondName", secondName);
            req.setAttribute("lastName", lastName);
            req.setAttribute("loanCards", loanCards);
            req.getRequestDispatcher(PathFile.USER_LOAN_CARD).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error when requesting the history of user cards.", e);
        }
    }
}
