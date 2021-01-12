package com.epam.pizza.action;

import com.epam.pizza.service.UserService;
import com.epam.pizza.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService(req);
        try {
            userService.getUpdateUser();
            return new LogoutAction().execute(req, resp);
        } catch (ServiceException e) {
            return new ShowPageAction("profile").execute(req, resp);
        }
    }
}
