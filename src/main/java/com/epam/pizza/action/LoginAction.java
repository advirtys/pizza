package com.epam.pizza.action;

import com.epam.pizza.entity.User;
import com.epam.pizza.service.UserService;
import com.epam.pizza.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        UserService userService = new UserService(req);
        try {
            User user = userService.getLoginUser();
            req.getSession(false).setAttribute("user", user);
        } catch (ServiceException e) {
            req.setAttribute("validate", true);
        }
        return new HomeAction("home").execute(req, resp);
    }
}
