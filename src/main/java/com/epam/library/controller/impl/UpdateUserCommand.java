package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.UPDATE_USER);
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String email = req.getParameter(Constant.USER_EMAIL);
            String secondName = req.getParameter(Constant.USER_SECOND_NAME);
            String lastName = req.getParameter(Constant.USER_LAST_NAME);
            String delete = req.getParameter(Constant.USERS_DELETE);
            User user = (User) session.getAttribute(Constant.USER);
            if (email != null && email != "" || secondName != null && secondName != ""
                    || lastName != null && lastName != "") {
                int resultOperation = userService.update(email, secondName, lastName, user.getUserId() + "");
                if (resultOperation == 1) {
                    session.setAttribute(Constant.MESSAGE_CODE_1019, Constant.MESSAGE_CODE_1019);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else if (resultOperation == 2){
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1024, Constant.MESSAGE_ERROR_CODE_1024);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else if (resultOperation == 3){
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1007, Constant.MESSAGE_ERROR_CODE_1007);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else if (delete != null) {
                userService.remove(user.getUserId() + "");
                session.invalidate();
                resp.sendRedirect(PathJsp.INDEX_PAGE);
            }else {
                req.getRequestDispatcher(PathJsp.UPDATE_USER_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("An error occurred while updating the user's personal data.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
