package com.epam.pizza.action;

import com.epam.pizza.action.Action;
import com.epam.pizza.action.ActionResult;
import com.epam.pizza.dao.ProductDAO;
import com.epam.pizza.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public class EditProductAction implements Action {

    private ActionResult result;

    public EditProductAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.selectById(id);
        productDAO.close();
        String[] titles = product.getTitle().split(" <<<>>> ");
        String[] desc = product.getDescription().split(" <<<>>> ");
        req.setAttribute("product", product);
        req.setAttribute("titles", titles);
        req.setAttribute("desc", desc);
        return result;
    }
}
