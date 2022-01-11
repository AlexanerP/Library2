package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.LoanCardService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionReturnBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionReturnBookCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.ACTION_RETURN_BOOK);
            String loanCardId = req.getParameter("loanCardId");
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            if (loanCardId != null) {
                boolean resultOperation = loanCardService.closeLoanCard(loanCardId);
                if (resultOperation) {
                    String successfulMessage = "Operation successful";
                    req.getSession().setAttribute("successfulMessage", successfulMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    String negativeMessage = "Operation failed";
                    req.getSession().setAttribute("negativeMessage", negativeMessage);
                    resp.sendRedirect("Controller?command=" + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.RETURN_BOOK_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error closing the issue book.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
