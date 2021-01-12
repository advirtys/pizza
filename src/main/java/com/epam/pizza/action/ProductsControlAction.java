package com.epam.pizza.action;

import com.epam.pizza.action.Action;
import com.epam.pizza.action.ActionResult;
import com.epam.pizza.dao.ProductDAO;
import com.epam.pizza.entity.Product;
import com.epam.pizza.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsControlAction implements Action {
    private ActionResult productsControl;
    public ProductsControlAction(String page) {
        productsControl = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String locale = Locale.getLocale(req);
        ProductDAO productDAO = new ProductDAO(locale);
        List<Product> products = productDAO.selectAll();
        productDAO.close();
        req.setAttribute("products", products);
        return productsControl;
    }
}
