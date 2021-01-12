package com.epam.pizza.action;

import com.epam.pizza.dao.UserDAO;
import com.epam.pizza.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserAction implements Action {
    private final static Logger logger = LoggerFactory.getLogger(UpdateUserAction.class);
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("update_user_id"));
        String newRole = req.getParameter("new_role");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.selectById(id);
        user.setRole(newRole);
        userDAO = new UserDAO();
        userDAO.updateEntity(user);
        logger.info("User update!");
        return new UsersControlAction("users-control").execute(req, resp);
    }
}
