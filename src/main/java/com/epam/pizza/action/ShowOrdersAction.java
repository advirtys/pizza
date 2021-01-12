package com.epam.pizza.action;

import com.epam.pizza.dao.OrderDAO;
import com.epam.pizza.entity.Order;
import com.epam.pizza.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOrdersAction implements Action {
    private ActionResult result;
    private final static Logger logger = LoggerFactory.getLogger(ShowOrdersAction.class);
    public ShowOrdersAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession(false).getAttribute("user");
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.selectById(user.getId());
        orderDAO.close();
        req.setAttribute("orderList", orderList);
        logger.info("Show orders for: " + user.getLogin());
        return result;
    }
}
