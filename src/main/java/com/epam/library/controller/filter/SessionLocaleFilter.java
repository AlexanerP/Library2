package com.epam.library.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {

    private final static String SESSION_LOCALE = "sessionLocale";
    private final static String LANGUAGE = "language";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getParameter(SESSION_LOCALE) != null) {
            req.getSession().setAttribute(LANGUAGE, req.getParameter(SESSION_LOCALE));
        }
System.out.println("FILTER - " + req.getParameter(SESSION_LOCALE));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
