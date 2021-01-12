package com.epam.pizza.action;

import com.epam.pizza.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("delete_user"));
        UserDAO userDAO = new UserDAO();
        userDAO.deleteEntity(id);
        return new UsersControlAction("users-control").execute(req, resp);
    }
}
