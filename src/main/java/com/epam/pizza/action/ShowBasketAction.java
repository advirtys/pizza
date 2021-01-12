package com.epam.pizza.action;

import com.epam.pizza.entity.Order;
import com.epam.pizza.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBasketAction implements Action {

    private ActionResult result;

    public ShowBasketAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession(false).getAttribute("user");
        Order order = (Order) req.getSession(false).getAttribute("order");
        order.setUser(user);
        req.getSession(false).setAttribute("order", order);

        return result;
    }
}
