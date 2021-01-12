package com.epam.pizza.action;

import com.epam.pizza.dao.UserDAO;
import com.epam.pizza.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersControlAction implements Action {
    private ActionResult usersControl;
    public UsersControlAction(String page) {
        usersControl = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.selectAll();
        req.setAttribute("users", users);
        return usersControl;
    }
}
