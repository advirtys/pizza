package com.epam.pizza.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp);
}

