package com.epam.library.controller.filter;

import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleFilter implements Filter {

    private final static Map<String, List<UserRole>> commandsUser = new HashMap<>();
    private final static String COMMAND = "command";
    private final static String USER = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        List<UserRole> allUsers = Arrays.asList(UserRole.USER, UserRole.ADMIN, UserRole.MANAGER);
        List<UserRole> adminManager = Arrays.asList(UserRole.ADMIN, UserRole.MANAGER);
        List<UserRole> manager = Arrays.asList(UserRole.MANAGER);

        commandsUser.put(CommandType.GO_TO_HOME, allUsers);
        commandsUser.put(CommandType.GO_TO_CATALOG, allUsers);
        commandsUser.put(CommandType.GO_TO_ORDER, allUsers);
        commandsUser.put(CommandType.ORDER_BOOK, allUsers);
        commandsUser.put(CommandType.SHOW_CATALOG_BY_PAGE, allUsers);
        commandsUser.put(CommandType.LOAN_CARD_USER, allUsers);
        commandsUser.put(CommandType.UPDATE_USER, allUsers);
        commandsUser.put(CommandType.UPDATE_USER_PASSWORD, allUsers);
        commandsUser.put(CommandType.ORDER_USER, allUsers);
        commandsUser.put(CommandType.WISH_BOOKS_USER_PAGE, allUsers);
        commandsUser.put(CommandType.GO_TO_MESSAGE_PAGE, allUsers);
        commandsUser.put(CommandType.ACTION_USER_ORDER, allUsers);
        commandsUser.put(CommandType.SIGN_OUT, allUsers);
        commandsUser.put(CommandType.ACTION_WISH_BOOK, allUsers);
        commandsUser.put(CommandType.GO_TO_CATALOG_BY_PAGE, allUsers);

        commandsUser.put(CommandType.MANAGER_CATALOG, manager);
        commandsUser.put(CommandType.ACTION_ADMIN_COMMAND, manager);
        commandsUser.put(CommandType.LIBRARY_CATALOG, manager);
        commandsUser.put(CommandType.GO_TO_UPDATE_LIBRARY, manager);
        commandsUser.put(CommandType.ACTION_LIBRARY, manager);
        commandsUser.put(CommandType.UPDATE_LIBRARY, manager);
        commandsUser.put(CommandType.CREATE_LIBRARY, manager);

        commandsUser.put(CommandType.ACTION_GIVE_OUT_BOOK, adminManager);
        commandsUser.put(CommandType.ACTION_RETURN_BOOK, adminManager);
        commandsUser.put(CommandType.GO_TO_ADMIN_PAGE, adminManager);
        commandsUser.put(CommandType.ADMIN_PAGE, adminManager);
        commandsUser.put(CommandType.GIVE_OUT_BOOK_USER, adminManager);
        commandsUser.put(CommandType.RETURN_BOOK_CATALOG, adminManager);
        commandsUser.put(CommandType.CATALOG_BOOK, adminManager);
        commandsUser.put(CommandType.GO_TO_UPDATE_BOOK, adminManager);
        commandsUser.put(CommandType.UPDATE_BOOK, adminManager);
        commandsUser.put(CommandType.ACTION_AUTHOR, adminManager);
        commandsUser.put(CommandType.GO_TO_STATISTIC_AUTHOR, adminManager);
        commandsUser.put(CommandType.ACTION_GENRE, adminManager);
        commandsUser.put(CommandType.GO_TO_STATISTIC_GENRE, adminManager);
        commandsUser.put(CommandType.LOAN_CARD_CATALOG, adminManager);
        commandsUser.put(CommandType.USER_CATALOG, adminManager);
        commandsUser.put(CommandType.ACTION_USER, adminManager);
        commandsUser.put(CommandType.ORDER_CATALOG, adminManager);
        commandsUser.put(CommandType.CREATE_BOOK, adminManager);
        commandsUser.put(CommandType.ACTION_ORDER, adminManager);
        commandsUser.put(CommandType.GO_TO_STATISTICS, adminManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter(COMMAND);
        User user = (User) request.getSession().getAttribute(USER);
        if (user != null) {
            UserRole userRole = user.getRole();
            List<UserRole> roles = commandsUser.get(command);
            if (roles != null && !roles.contains(userRole)) {
                request.getRequestDispatcher(PathJsp.INDEX_PAGE).forward(request, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
