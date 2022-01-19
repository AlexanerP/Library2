package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
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

public class ActionGiveOutBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionGiveOutBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ACTION_GIVE_OUT_BOOK);
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            String orderId = req.getParameter(Constant.ORDER_ID);
            String typeUseBook = req.getParameter(Constant.ORDER_TYPE_USE);

            if (orderId != null && typeUseBook != null) {
                boolean resultOperation = loanCardService.create(orderId, typeUseBook);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1008, Constant.MESSAGE_CODE_1008);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1013, Constant.MESSAGE_ERROR_CODE_1013);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.GIVE_OUT_BOOK_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error while issuing a book.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
