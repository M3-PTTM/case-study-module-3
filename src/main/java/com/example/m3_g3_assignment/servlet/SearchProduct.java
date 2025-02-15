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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@WebServlet("/search")
public class SearchProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ServiceProduct SERVICE_PRODUCT = new ServiceProductImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        NumberFormat formatter = new DecimalFormat("###,###");
        try {
            String keyword = req.getParameter("keyword").trim();
            List<Product> products;
            PrintWriter out;
            int count = 1;
            if (keyword.isEmpty()) {
                products = SERVICE_PRODUCT.getProductsNewLimit3();
                out = resp.getWriter();
                for (Product product : products) {
                    String formattedPrice = formatter.format(product.getPrice());
                    if (product.getInventory() > 0) {
                        if (count % 2 != 0) {
                            out.println("<div class=\"product cycle_section_3 layout_padding\">\n" +
                                    "                            <div class=\"row\">\n" +
                                    "                                <div class=\"col-md-6\">\n" +
                                    "                                    <div class=\"box_main_3\">\n" +
                                    "                                        <a href=\"/home?action=view&id="+product.getId()+"\"><h6 class=\"number_text\"></h6>\n" +
                                    "                                            <div class=\"image_2\"><img src=\"/man/images/"+product.getImg()+"\"></div>\n" +
                                    "                                        </a>\n" +
                                    "                                    </div>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"col-md-6\">\n" +
                                    "                                    <a href=\"/home?action=view&id="+product.getId()+"\"><h1\n" +
                                    "                                            class=\"cycles_text\">"+product.getCategory()+" "+product.getName()+"</h1>\n" +
                                    "                                        <p class=\"lorem_text\">"+product.getDescription()+"</p></a>\n" +
                                    "                                    <div class=\"btn_main\">\n" +
                                    "                                        <div class=\"buy_bt\"><a href=\"#\" class=\"buy-now\" data-id=\""+product.getId()+"\">Giỏ\n" +
                                    "                                            Hàng</a></div>\n" +
                                    "                                        <h4 class=\"price_text\"><span\n" +
                                    "                                                style=\" color: #325662\">"+formattedPrice+"</span><span\n" +
                                    "                                                style=\" color: #f7c17b\">VND</span></h4>\n" +
                                    "                                    </div>\n" +
                                    "                                </div>\n" +
                                    "                            </div>\n" +
                                    "                        </div>");
                            count++;
                        } else {
                            out.println("<div class=\"product cycle_section_3 layout_padding\">\n" +
                                    "                            <div class=\"row\">\n" +
                                    "                                <div class=\"col-md-6\">\n" +
                                    "                                    <a href=\"/home?action=view&id=" + product.getId() + "\"><h1\n" +
                                    "                                            class=\"cycles_text\">" + product.getCategory() + " " + product.getName() + "</h1>\n" +
                                    "                                        <p class=\"lorem_text\">" + product.getDescription() + "</p></a>\n" +
                                    "                                    <div class=\"btn_main\">\n" +
                                    "                                        <div class=\"buy_bt\"><a href=\"#\" class=\"buy-now\" data-id=\"" + product.getId() + "\">Giỏ\n" +
                                    "                                            Hàng</a></div>\n" +
                                    "                                        <h4 class=\"price_text\"><span\n" +
                                    "                                                style=\" color: #325662\">"+formattedPrice+"</span><span\n" +
                                    "                                                style=\" color: #f7c17b\">VND</span></h4>\n" +
                                    "                                    </div>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"col-md-6\">\n" +
                                    "                                    <div class=\"box_main_3\">\n" +
                                    "                                        <a href=\"/home?action=view&id=" + product.getId() + "\"><h6 class=\"number_text\"></h6>\n" +
                                    "                                            <div class=\"image_2\"><img src=\"/man/images/" + product.getImg() + "\"></div>\n" +
                                    "                                        </a>\n" +
                                    "                                    </div>\n" +
                                    "                                </div>\n" +
                                    "                            </div>\n" +
                                    "                        </div>");
                            count++;
                        }
                    }
                }
            } else {
                products = SERVICE_PRODUCT.searchProductsByNameOrModel(keyword);
                out = resp.getWriter();
                if (!products.isEmpty()) {
                    for (Product product : products) {
                        String formattedPrice = formatter.format(product.getPrice());
                        if (product.getInventory() > 0) {
                            if (count % 2 != 0) {
                                out.println("<div class=\"product cycle_section_3 layout_padding\">\n" +
                                        "                            <div class=\"row\">\n" +
                                        "                                <div class=\"col-md-6\">\n" +
                                        "                                    <div class=\"box_main_3\">\n" +
                                        "                                        <a href=\"/home?action=view&id=" + product.getId() + "\"><h6 class=\"number_text\"></h6>\n" +
                                        "                                            <div class=\"image_2\"><img src=\"/man/images/" + product.getImg() + "\"></div>\n" +
                                        "                                        </a>\n" +
                                        "                                    </div>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"col-md-6\">\n" +
                                        "                                    <a href=\"/home?action=view&id=" + product.getId() + "\"><h1\n" +
                                        "                                            class=\"cycles_text\">" + product.getCategory() + " " + product.getName() + "</h1>\n" +
                                        "                                        <p class=\"lorem_text\">" + product.getDescription() + "</p></a>\n" +
                                        "                                    <div class=\"btn_main\">\n" +
                                        "                                        <div class=\"buy_bt\"><a href=\"#\" class=\"buy-now\" data-id=\"" + product.getId() + "\">Giỏ\n" +
                                        "                                            Hàng</a></div>\n" +
                                        "                                        <h4 class=\"price_text\"><span\n" +
                                        "                                                style=\" color: #325662\">"+formattedPrice+"</span><span\n" +
                                        "                                                style=\" color: #f7c17b\">VND</span></h4>\n" +
                                        "                                    </div>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </div>");
                                count++;
                            } else {
                                out.println("<div class=\"product cycle_section_3 layout_padding\">\n" +
                                        "                            <div class=\"row\">\n" +
                                        "                                <div class=\"col-md-6\">\n" +
                                        "                                    <a href=\"/home?action=view&id=" + product.getId() + "\"><h1\n" +
                                        "                                            class=\"cycles_text\">" + product.getCategory() + " " + product.getName() + "</h1>\n" +
                                        "                                        <p class=\"lorem_text\">" + product.getDescription() + "</p></a>\n" +
                                        "                                    <div class=\"btn_main\">\n" +
                                        "                                        <div class=\"buy_bt\"><a href=\"#\" class=\"buy-now\" data-id=\"" + product.getId() + "\">Giỏ\n" +
                                        "                                            Hàng</a></div>\n" +
                                        "                                        <h4 class=\"price_text\"><span\n" +
                                        "                                                style=\" color: #325662\">"+formattedPrice+"</span><span\n" +
                                        "                                                style=\" color: #f7c17b\">VND</span></h4>\n" +
                                        "                                    </div>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"col-md-6\">\n" +
                                        "                                    <div class=\"box_main_3\">\n" +
                                        "                                        <a href=\"/home?action=view&id=" + product.getId() + "\"><h6 class=\"number_text\"></h6>\n" +
                                        "                                            <div class=\"image_2\"><img src=\"/man/images/" + product.getImg() + "\"></div>\n" +
                                        "                                        </a>\n" +
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
