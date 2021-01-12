package com.epam.pizza.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocaleInstallerServlet extends HttpServlet {
    private Map<String, String> refMap;

    @Override
    public void init() throws ServletException {
        refMap = new HashMap<>();
        refMap.put("login", "");
        refMap.put("logout", "");
        refMap.put("update-user", "");
        refMap.put("edit-product", "");
        refMap.put("basket-action", "basket");
        refMap.put("select-orders", "orders-control");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locale = req.getParameter("locale");
        req.getSession().setAttribute("locale", locale);
        String referer = req.getHeader("Referer");

        for (Map.Entry<String, String> entry : refMap.entrySet()) {
            if (referer.endsWith(entry.getKey())) {
                referer = referer.replace(entry.getKey(), entry.getValue());
            }
        }
        resp.sendRedirect(referer);
    }
}
