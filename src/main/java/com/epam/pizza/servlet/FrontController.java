package com.epam.pizza.servlet;

import com.epam.pizza.action.Action;
import com.epam.pizza.action.ActionFactory;
import com.epam.pizza.action.ActionResult;
import com.epam.pizza.entity.Order;
import com.epam.pizza.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class FrontController extends HttpServlet {

    private ActionFactory factory;
    @Override
    public void init() throws ServletException {
        factory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            user = new User("guest");
            req.getSession(false).setAttribute("user", user);
        } else {
            req.getSession(false).setAttribute("user", user);
        }

        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = new Order();
            order.setDate(new Date(new java.util.Date().getTime()));
            req.getSession(false).setAttribute("order", order);
        } else {
            order.setDate(new Date(new java.util.Date().getTime()));
            req.getSession(false).setAttribute("order", order);
        }

        Action action = factory.getAction(req);
        ActionResult res = action.execute(req, resp);
        doForwardOrRedirect(res, req, resp);


    }

    private void doForwardOrRedirect(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (result.isRedirect()) {
            resp.sendRedirect(result.getView());
        } else {
            String path = "/WEB-INF/jsp/" + result.getView() + ".jsp";
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}
