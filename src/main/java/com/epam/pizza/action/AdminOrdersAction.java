package com.epam.pizza.action;

import com.epam.pizza.action.Action;
import com.epam.pizza.action.ActionResult;
import com.epam.pizza.dao.OrderDAO;
import com.epam.pizza.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminOrdersAction implements Action {
    private ActionResult result;

    public AdminOrdersAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        OrderDAO orderDAO = new OrderDAO();

        String select = req.getParameter("select");

        if (select != null && select.equals("all")) {
            orderDAO.setParameter("all");
            req.setAttribute("allCheck", true);
        } else if (select != null && select.equals("delivered")) {
            orderDAO.setParameter("delivered");
            req.setAttribute("deliveredCheck", true);
        } else {
            orderDAO.setParameter("new");
            req.setAttribute("newCheck", true);
        }

        List<Order> orderList = orderDAO.selectAll();
        orderDAO.close();
        req.setAttribute("orderList", orderList);
        return result;
    }
}
