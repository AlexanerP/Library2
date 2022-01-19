package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.GenreService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateGenreCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateGenreCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.UPDATE_AUTHOR);
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            Long genreId = (Long) req.getSession().getAttribute(Constant.UPDATE_GENRE_ID);
            String category = req.getParameter(Constant.GENRE_NAME);
            req.getSession().removeAttribute(Constant.UPDATE_AUTHOR_ID);
            System.out.println("genreId/" + genreId + ". category/" + category);
            if (genreId != null && category != null) {
                int resultOperation = genreService.update(genreId + "", category);
                if (resultOperation == 1) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1003, Constant.MESSAGE_CODE_1003);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else if (resultOperation == 2){
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1008, Constant.MESSAGE_ERROR_CODE_1008);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }else if (resultOperation == 3){
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1007, Constant.MESSAGE_ERROR_CODE_1007);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error while updating the genre.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
