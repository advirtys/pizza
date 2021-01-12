package com.epam.pizza.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandlerServlet.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        int statusCode = (int) req.getAttribute("javax.servlet.error.status_code");
        String reqUri = (String) req.getAttribute("javax.servlet.error.request_uri");

        if (reqUri != null) {
            reqUri = "Unknown";
        }

        req.setAttribute("statusCode", statusCode);
        req.setAttribute("reqUri", reqUri);
        String path = "/WEB-INF/jsp/error-page.jsp";

        req.getRequestDispatcher(path).forward(req, resp);
        logger.error("Throwable message: " + throwable.getMessage() + "\n " +
                "Status code: " + statusCode + "\n " +
                "Request URI: " + reqUri);
    }
}
