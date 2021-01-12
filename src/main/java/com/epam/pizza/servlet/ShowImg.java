package com.epam.pizza.servlet;

import com.epam.pizza.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet (name = "Show-Img",urlPatterns = "/show-img")
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class ShowImg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        int id = Integer.parseInt(req.getParameter("img"));
        ProductDAO productDAO = new ProductDAO();
        InputStream is = productDAO.getImage(id);
        productDAO.close();

        ServletOutputStream os = resp.getOutputStream();
        while (is.available() > 0) {
            os.write(is.read());
        }

        is.close();
        os.close();


    }
}
