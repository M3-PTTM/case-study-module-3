package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ICustomerDAO;
import com.example.m3_g3_assignment.dao.ServiceCart;
import com.example.m3_g3_assignment.dao.ServiceProduct;
import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.dao.impl.ServiceCartImpl;
import com.example.m3_g3_assignment.dao.impl.ServiceProductImpl;
import com.example.m3_g3_assignment.model.Cart;
import com.example.m3_g3_assignment.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cart")
public class LoadCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceProduct SERVICE_PRODUCT = new ServiceProductImpl();
    private static final ServiceCart SERVICE_CART = new ServiceCartImpl();
    private static final ICustomerDAO I_CUSTOMER_DAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        int productId = Integer.parseInt(req.getParameter("id"));
        int customerId = 1;
        try {
            SERVICE_CART.removeProduct(customerId, productId);
            List<Product> products = SERVICE_CART.getAllProducts(customerId);
            PrintWriter out = resp.getWriter();
            int count = 1;
            for (Product product : products) {
                if (count % 2 != 0) {
                    out.println("<div class=\"product cycle_section_3 layout_padding\" id=\"product" + product.getId() + "\">\n" +
                            "                            <div class=\"row\">\n" +
                            "                                <div class=\"col-md-6\">\n" +
                            "                                    <div class=\"box_main_3\">\n" +
                            "                                        <a href=\"/home?action=view&id=" + product.getId() + "\"><h6\n" +
                            "                                                class=\"number_text\">" + count + "</h6>\n" +
                            "                                            <div class=\"image_2\"><img src=\"/man/images/" + product.getImg() + "\"></div>\n" +
                            "                                        </a>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                                <div class=\"col-md-6\">\n" +
                            "                                    <a href=\"/home?action=view&id=" + product.getId() + "\"><h1\n" +
                            "                                            class=\"cycles_text\">" + product.getCategory() + " " + product.getName() + "</h1>\n" +
                            "                                        <p class=\"lorem_text\">" + product.getDescription() + "</p></a>\n" +
                            "                                    <div class=\"btn_main\">\n" +
                            "                                        <div class=\"buy_bt\">\n" +
                            "                                            <div class=\"quantity_selector\">\n" +
                            "                                                <button class=\"quantity_button\"\n" +
                            "                                                        onclick=\"decreaseQuantity(" + product.getId() + ")\">-\n" +
                            "                                                </button>\n" +
                            "                                                <input type=\"number\" id=\"quantity" + product.getId() + "\" value=\"" + product.getQuantity() + "\"\n" +
                            "                                                       min=\"1\"\n" +
                            "                                                       max=\"" + product.getInventory() + "\" class=\"quantity_input\"\n" +
                            "                                                       onchange=\"updateTotalPrice()\"/>\n" +
                            "                                                <button class=\"quantity_button\"\n" +
                            "                                                        onclick=\"increaseQuantity(" + product.getId() + ")\">+\n" +
                            "                                                </button>\n" +
                            "                                            </div>\n" +
                            "                                        </div>\n" +
                            "                                        <h4 class=\"price_text\">\n" +
                            "                                            <span style=\" color: #325662\">" + product.getPrice() + "</span>\n" +
                            "                                            <span style=\" color: #f7c17b\"> USD</span>\n" +
                            "                                        </h4>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>");
                    count++;
                } else {
                    out.println("  <div class=\"product cycle_section_3 layout_padding\" id=\"product" + product.getId() + "\">\n" +
                            "                            <div class=\"row\">\n" +
                            "                                <div class=\"col-md-6\">\n" +
                            "                                    <a href=\"/home?action=view&id=" + product.getId() + "\"><h1\n" +
                            "                                            class=\"cycles_text\">" + product.getCategory() + " " + product.getName() + "</h1>\n" +
                            "                                        <p class=\"lorem_text\">" + product.getDescription() + "</p></a>\n" +
                            "                                    <div class=\"btn_main\">\n" +
                            "                                        <div class=\"buy_bt\">\n" +
                            "                                            <div class=\"quantity_selector\">\n" +
                            "                                                <button class=\"quantity_button\"\n" +
                            "                                                        onclick=\"decreaseQuantity(" + product.getId() + ")\">-\n" +
                            "                                                </button>\n" +
                            "                                                <input type=\"number\" id=\"quantity" + product.getId() + "\" value=\"" + product.getQuantity() + "\"\n" +
                            "                                                       min=\"1\"\n" +
                            "                                                       max=\"" + product.getInventory() + "\" class=\"quantity_input\"\n" +
                            "                                                       onchange=\"updateTotalPrice()\"/>\n" +
                            "                                                <button class=\"quantity_button\"\n" +
                            "                                                        onclick=\"increaseQuantity(" + product.getId() + ")\">+\n" +
                            "                                                </button>\n" +
                            "                                            </div>\n" +
                            "                                        </div>\n" +
                            "                                        <h4 class=\"price_text\">\n" +
                            "                                            <span style=\" color: #325662\">" + product.getPrice() + "</span>\n" +
                            "                                            <span style=\" color: #f7c17b\"> USD</span>\n" +
                            "                                        </h4>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                                <div class=\"col-md-6\">\n" +
                            "                                    <div class=\"box_main_3\">\n" +
                            "                                        <a href=\"/home?action=view&id=" + product.getId() + "\"><h6\n" +
                            "                                                class=\"number_text\">" + count + "</h6>\n" +
                            "                                            <div class=\"image_2\"><img src=\"/man/images/" + product.getImg() + "\"></div>\n" +
                            "                                        </a>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>");
                    count++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int customerId = 1;
        int productId = Integer.parseInt(req.getParameter("id"));
        Cart cart = new Cart(quantity, customerId, productId);
        try {
            SERVICE_CART.updateCart(cart);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
