package com.epam.pizza.action;

import com.epam.pizza.dao.UserDAO;
import com.epam.pizza.entity.User;
import com.epam.pizza.service.UserService;
import com.epam.pizza.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {
    private final static Logger logger = LoggerFactory.getLogger(RegisterAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(req);
        User user;
        try {
            user = userService.getRegisteringUser();
        } catch (ServiceException e) {
            req.setAttribute("validate", true);
            return new ActionResult("register");
        }
        userDAO.insertEntity(user);
        req.setAttribute("welcome", true);
        logger.info("Register user!");
        return new HomeAction("home").execute(req, resp);

    }
}

