package com.epam.pizza.action;

import com.epam.pizza.dao.ProductDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteEntity(id);
        return new ProductsControlAction("products-control").execute(req, resp);
    }
}
