package com.epam.pizza.action;

import com.epam.pizza.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(false).invalidate();
        User user = new User("guest");
        req.getSession().setAttribute("user", user);
        return new HomeAction("home").execute(req, resp);
    }
}
