package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.AuthorService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateAuthorCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateAuthorCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.UPDATE_AUTHOR);
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            Long authorId = (Long) req.getSession().getAttribute(Constant.UPDATE_AUTHOR_ID);
            String name = req.getParameter(Constant.AUTHOR_NAME);
            req.getSession().removeAttribute(Constant.UPDATE_AUTHOR_ID);
            if (authorId != null && name != null) {
                int resultOperation = authorService.update(authorId + "", name);
                if (resultOperation == 1) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1002, Constant.MESSAGE_CODE_1002);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else if (resultOperation == 2){
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1006, Constant.MESSAGE_ERROR_CODE_1006);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }else if (resultOperation == 3){
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1007, Constant.MESSAGE_ERROR_CODE_1007);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error while updating the author.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
