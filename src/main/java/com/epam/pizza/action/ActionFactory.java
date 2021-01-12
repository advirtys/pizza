package com.epam.pizza.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Logger logger = LoggerFactory.getLogger(ActionFactory.class);
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/", new HomeAction("home"));
        actions.put("GET/contacts", new ShowPageAction("contacts"));
        actions.put("GET/product", new ProductAction("product"));
        actions.put("GET/register", new RedirectPageAction("register"));
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/profile", new RedirectPageAction("profile"));
        actions.put("POST/profile", new ProfileAction());
        actions.put("GET/control", new RedirectPageAction("control"));
        actions.put("GET/users-control", new RedirectPageAction("users-control"));
        actions.put("POST/update-user", new UpdateUserAction());
        actions.put("POST/delete-user", new DeleteUserAction());
        actions.put("GET/products-control", new RedirectPageAction("products-control"));
        actions.put("POST/delete-product", new DeleteProductAction());
        actions.put("POST/update-product", new UpdateProductAction("control"));
        actions.put("GET/add-product", new RedirectPageAction("add-product"));
        actions.put("POST/add-product", new AddProductAction());
        actions.put("POST/edit-product", new EditProductAction("edit-product"));
        actions.put("POST/add-basket", new AddBasketProductAction());
        actions.put("GET/basket", new RedirectPageAction("basket"));
        actions.put("POST/basket-action", new BasketDoAction());
        actions.put("GET/address", new RedirectPageAction("address"));
        actions.put("POST/address", new AddressAction("pay"));
        actions.put("POST/pay", new PayAction(""));
        actions.put("GET/user-orders", new RedirectPageAction("user-orders"));
        actions.put("GET/orders-control", new RedirectPageAction("orders-control"));
        actions.put("POST/select-orders", new RedirectPageAction("orders-control"));
        actions.put("POST/update-status", new UpdateOrderAction("orders-control"));

    }

    public Action getAction(HttpServletRequest req) {
        String key = req.getMethod() + req.getPathInfo();
        logger.info(key);
        return actions.get(key);
    }

}
