package com.epam.pizza.action;

import com.epam.pizza.action.Action;
import com.epam.pizza.action.ActionResult;
import com.epam.pizza.dao.ProductDAO;
import com.epam.pizza.entity.Product;
import com.epam.pizza.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAction implements Action {
    private ActionResult product;

    private Product.Type pizzaType = Product.Type.PIZZA;
    private Product.Type sushiType = Product.Type.SUSHI;
    private Product.Type drinkType = Product.Type.DRINK;


    public ProductAction(String page) {
        product = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String type = req.getParameter("type").toUpperCase();
        String locale = Locale.getLocale(req);

        Map<String, List<Product>> mapProduct = getProductList(locale);
        List<Product> listProduct = mapProduct.get(type);

        req.setAttribute("titlePage", getTitlePage(type, locale));
        req.setAttribute("products", listProduct);

        return product;
    }

    private String getTitlePage(String type, String locale) {
        Map<String, String> mapTitleRu = new HashMap<>();

        mapTitleRu.put(pizzaType.toString(), "Пицца");
        mapTitleRu.put(sushiType.toString(), "Суши");
        mapTitleRu.put(drinkType.toString(), "Напитки");

        Map<String, String> mapTitleEn = new HashMap<>();

        mapTitleEn.put(pizzaType.toString(), "Pizza");
        mapTitleEn.put(sushiType.toString(), "Sushi");
        mapTitleEn.put(drinkType.toString(), "Drinks");

        if (locale.equals("en_US")) {
            return mapTitleEn.get(type);
        }
        return mapTitleRu.get(type);

    }

    private Map<String, List<Product>> getProductList(String locale) {
        Map<String, List<Product>> mapProduct = new HashMap<>();

        ProductDAO productDAO = new ProductDAO(locale);
        List<Product> products = productDAO.selectAll();
        productDAO.close();
        List<Product> pizzas = new ArrayList<>();
        List<Product> sushis = new ArrayList<>();
        List<Product> drinks = new ArrayList<>();

        for (Product product : products) {
            if (product.getType() == pizzaType) {
                pizzas.add(product);
            } else if (product.getType() == sushiType) {
                sushis.add(product);
            } else if (product.getType() == drinkType) {
                drinks.add(product);
            }
        }

        mapProduct.put(pizzaType.toString(), pizzas);
        mapProduct.put(sushiType.toString(), sushis);
        mapProduct.put(drinkType.toString(), drinks);

        return mapProduct;
    }
}
