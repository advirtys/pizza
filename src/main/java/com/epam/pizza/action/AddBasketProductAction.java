package com.epam.pizza.action;

import com.epam.pizza.action.Action;
import com.epam.pizza.action.ActionResult;
import com.epam.pizza.dao.ProductDAO;
import com.epam.pizza.entity.Order;
import com.epam.pizza.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddBasketProductAction implements Action {
    private List<String> refererReplaceList = new ArrayList<>();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        refererReplaceList.add("basket-action");
        refererReplaceList.add("login");

        int id = Integer.parseInt(req.getParameter("id"));

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.selectById(id);
        productDAO.close();


        Order order = (Order) req.getSession(false).getAttribute("order");
        order.add(product);
        req.getSession(false).setAttribute("order", order);

        String referer = req.getHeader("Referer");

        for (String ref : refererReplaceList) {
            if (referer.endsWith(ref)) {
                referer = referer.replace(ref, "");
            }
        }

        return new ActionResult(referer, true);
    }



}
