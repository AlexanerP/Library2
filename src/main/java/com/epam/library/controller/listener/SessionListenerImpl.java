package com.epam.library.controller.listener;

import com.epam.library.controller.Constant;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        User user = (User) session.getAttribute(Constant.USER);
        if (user == null) {
            user = new User();
            user.setRole(UserRole.GUEST);
            session.setAttribute(Constant.USER, user);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
