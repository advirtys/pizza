package com.epam.pizza.action;

import com.epam.pizza.action.Action;
import com.epam.pizza.action.ActionResult;
import com.epam.pizza.entity.Address;
import com.epam.pizza.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddressAction implements Action {
    private ActionResult result;
    public AddressAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Order order = (Order) req.getSession(false).getAttribute("order");
        Address address = new Address();
        address.setAddressee(req.getParameter("addressee"));
        address.setFlatNumber(Integer.parseInt(req.getParameter("flat")));
        address.setHouseNumber(Integer.parseInt(req.getParameter("house")));
        address.setStreet(req.getParameter("street"));
        address.setPhone(req.getParameter("phone"));

        order.setAddress(address);

        req.getSession(false).setAttribute("order", order);

        return result;
    }
}
