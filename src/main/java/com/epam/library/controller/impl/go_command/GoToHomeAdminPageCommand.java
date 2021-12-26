package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.User;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.BookService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToHomeAdminPageCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(GoToHomeAdminPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PathFile.HOME_ADMIN_PAGE).forward(req, resp);
    }
}
