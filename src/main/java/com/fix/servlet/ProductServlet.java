package com.fix.servlet;

import com.fix.dao.fabric.ProductDaoFabric;
import com.fix.models.Product;
import com.fix.dao.ProductDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductServlet extends HttpServlet {

    private final static String NEW_ACTION = "/new";
    private final static String PRODUCTS_ACTION = "/products";
    private final static String ADD_ACTION = "/add";

    private ProductDao productDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        productDao = ProductDaoFabric.getProductDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if (action.equals(NEW_ACTION)) {
                showNewForm(request, response);
            } else if (action.equals(PRODUCTS_ACTION)) {
                getProductsList(request, response);
            } else if (action.equals(ADD_ACTION)) {
                create(request, response);
            } else {
                response.sendRedirect(PRODUCTS_ACTION.substring(1));
            }
        } catch (SQLException ex) {
            throw new ServletException();
        }
    }

    private void getProductsList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProducts = productDao.findAll();

        request.setAttribute("listProduct", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductForm.jsp");
        dispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(name, price);
        productDao.create(product);

        response.sendRedirect(PRODUCTS_ACTION.substring(1));
    }
}
