package com.epam.pizza.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageAction implements Action {

    private ActionResult resultPage;

    public ShowPageAction(String page) {
        resultPage = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return resultPage;
    }
}
