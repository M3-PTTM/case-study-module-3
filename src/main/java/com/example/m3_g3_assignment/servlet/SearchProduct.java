package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.ServiceProduct;
import com.example.m3_g3_assignment.dao.impl.ServiceProductImpl;
import com.example.m3_g3_assignment.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/search")
public class SearchProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceProduct SERVICE_PRODUCT = new ServiceProductImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        try {
            String keyword = req.getParameter("keyword").trim();
            Map<Product, Integer> map;
            PrintWriter out;
            int count = 1;
            if (keyword.isEmpty()) {
                map = SERVICE_PRODUCT.getProductsNewLimit3();
                out = resp.getWriter();
                for (Map.Entry<Product, Integer> entry : map.entrySet()) {
                    if (entry.getValue() > 0) {
                        if (count % 2 != 0) {
                            out.println("<div class=\"product cycle_section_3 layout_padding\">\n" +
                                    "                            <div class=\"row\">\n" +
                                    "                                <div class=\"col-md-6\">\n" +
                                    "                                    <div class=\"box_main_3\">\n" +
                                    "                                        <h6 class=\"number_text\">" + count + "</h6>\n" +
                                    "                                        <div class=\"image_2\"><img src=\"/man/images/" + entry.getKey().getImg() + "\"></div>\n" +
                                    "                                    </div>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"col-md-6\">\n" +
                                    "                                    <h1 class=\"cycles_text\">" + entry.getKey().getCategory() + " " + entry.getKey().getName() + "</h1>\n" +
                                    "                                    <p class=\"lorem_text\">" + entry.getKey().getDescription() + "</p>\n" +
                                    "                                    <div class=\"btn_main\">\n" +
                                    "                                        <div class=\"buy_bt\"><a href=\"#\">Buy Now</a></div>\n" +
                                    "                                        <h4 class=\"price_text\">Price <span style=\" color: #f7c17b\">$</span> <span\n" +
                                    "                                                style=\" color: #325662\">" + entry.getKey().getPrice() + "</span></h4>\n" +
                                    "                                    </div>\n" +
                                    "                                </div>\n" +
                                    "                            </div>\n" +
                                    "                        </div>\n");
                            count++;
                        } else {
                            out.println("<div class=\"product cycle_section_3 layout_padding\">\n" +
                                    "                            <div class=\"row\">\n" +
                                    "                                <div class=\"col-md-6\">\n" +
                                    "                                    <h1 class=\"cycles_text\">" + entry.getKey().getCategory() + " " + entry.getKey().getName() + "</h1>\n" +
                                    "                                    <p class=\"lorem_text\">" + entry.getKey().getDescription() + "</p>\n" +
                                    "                                    <div class=\"btn_main\">\n" +
                                    "                                        <div class=\"buy_bt\"><a href=\"#\">Buy Now</a></div>\n" +
                                    "                                        <h4 class=\"price_text\">Price <span style=\" color: #f7c17b\">$</span> <span\n" +
                                    "                                                style=\" color: #325662\">" + entry.getKey().getPrice() + "</span></h4>\n" +
                                    "                                    </div>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"col-md-6\">\n" +
                                    "                                    <div class=\"box_main_3\">\n" +
                                    "                                        <h6 class=\"number_text\">" + count + "</h6>\n" +
                                    "                                        <div class=\"image_2\"><img src=\"/man/images/" + entry.getKey().getImg() + "\"></div>\n" +
                                    "                                    </div>\n" +
                                    "                                </div>\n" +
                                    "                            </div>\n" +
                                    "                        </div>");
                            count++;
                        }
                    }
                }
            } else {
                map = SERVICE_PRODUCT.searchProductsByNameOrModel(keyword);
                out = resp.getWriter();
                if (!map.isEmpty()) {
                    for (Map.Entry<Product, Integer> entry : map.entrySet()) {
                        if (entry.getValue() > 0) {
                            if (count % 2 != 0) {
                                out.println("<div class=\"product cycle_section_3 layout_padding\">\n" +
                                        "                            <div class=\"row\">\n" +
                                        "                                <div class=\"col-md-6\">\n" +
                                        "                                    <div class=\"box_main_3\">\n" +
                                        "                                        <h6 class=\"number_text\">" + count + "</h6>\n" +
                                        "                                        <div class=\"image_2\"><img src=\"/man/images/" + entry.getKey().getImg() + "\"></div>\n" +
                                        "                                    </div>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"col-md-6\">\n" +
                                        "                                    <h1 class=\"cycles_text\">" + entry.getKey().getCategory() + " " + entry.getKey().getName() + "</h1>\n" +
                                        "                                    <p class=\"lorem_text\">" + entry.getKey().getDescription() + "</p>\n" +
                                        "                                    <div class=\"btn_main\">\n" +
                                        "                                        <div class=\"buy_bt\"><a href=\"#\">Buy Now</a></div>\n" +
                                        "                                        <h4 class=\"price_text\">Price <span style=\" color: #f7c17b\">$</span> <span\n" +
                                        "                                                style=\" color: #325662\">" + entry.getKey().getPrice() + "</span></h4>\n" +
                                        "                                    </div>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </div>\n");
                                count++;
                            } else {
                                out.println("<div class=\"product cycle_section_3 layout_padding\">\n" +
                                        "                            <div class=\"row\">\n" +
                                        "                                <div class=\"col-md-6\">\n" +
                                        "                                    <h1 class=\"cycles_text\">" + entry.getKey().getCategory() + " " + entry.getKey().getName() + "</h1>\n" +
                                        "                                    <p class=\"lorem_text\">" + entry.getKey().getDescription() + "</p>\n" +
                                        "                                    <div class=\"btn_main\">\n" +
                                        "                                        <div class=\"buy_bt\"><a href=\"#\">Buy Now</a></div>\n" +
                                        "                                        <h4 class=\"price_text\">Price <span style=\" color: #f7c17b\">$</span> <span\n" +
                                        "                                                style=\" color: #325662\">" + entry.getKey().getPrice() + "</span></h4>\n" +
                                        "                                    </div>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"col-md-6\">\n" +
                                        "                                    <div class=\"box_main_3\">\n" +
                                        "                                        <h6 class=\"number_text\">" + count + "</h6>\n" +
                                        "                                        <div class=\"image_2\"><img src=\"/man/images/" + entry.getKey().getImg() + "\"></div>\n" +
                                        "                                    </div>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </div>");
                                count++;
                            }
                        }
                    }
                } else {
                    out.println("<div class=\"container mt-5\">\n" +
                            "  <div class=\"alert alert-warning\" role=\"alert\">\n" +
                            "    Không có sản phẩm nào!\n" +
                            "  </div>\n" +
                            "</div>");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
