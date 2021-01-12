package com.epam.pizza.action;

import com.epam.pizza.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasketDoAction implements Action {
    private ActionResult result;
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Order order = (Order) req.getSession(false).getAttribute("order");

        String clear = req.getParameter("clear");
        String buy = req.getParameter("buy");
        if (clear != null && clear.equals("clear")) {
            order.remove();
            result = new HomeAction("home").execute(req, resp);
        } else if (buy != null && buy.equals("buy")) {
            result = new ActionResult("address");
        }
        return result;
    }
}
