package com.wipro.restaurant.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.wipro.restaurant.bean.RestaurantBean;
import com.wipro.restaurant.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    public String addRecord(HttpServletRequest request) {

        RestaurantBean bean = new RestaurantBean();
        String result = "";

        try {
            bean.setCustomerName(request.getParameter("customerName"));
            bean.setFoodItem(request.getParameter("foodItem"));
            bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(request.getParameter("orderDate"));
            bean.setOrderDate(d);

            bean.setTotalAmount(Integer.parseInt(request.getParameter("totalAmount")));
            bean.setRemarks(request.getParameter("remarks"));

            Administrator admin = new Administrator();
            result = admin.addRecord(bean);

        } catch (Exception e) {
            result = "FAIL";
        }

        return result;
    }

    public RestaurantBean viewRecord(HttpServletRequest request) {

        RestaurantBean bean = null;

        try {
            String name = request.getParameter("customerName");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(request.getParameter("orderDate"));

            Administrator admin = new Administrator();
            bean = admin.viewRecord(name, d);

        } catch (Exception e) {
            bean = null;
        }

        return bean;
    }

    public List<RestaurantBean> viewAllRecords(HttpServletRequest request) {

        Administrator admin = new Administrator();
        return admin.viewAllRecords();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if (operation.equals("newRecord")) {

            String result = addRecord(request);

            if (result.equals("FAIL") ||
                result.equals("INVALID INPUT") ||
                result.equals("ALREADY EXISTS") ||
                result.equals("INVALID CUSTOMER NAME") ||
                result.equals("INVALID DATE")) {

                response.sendRedirect("error.html");
            } else {
                response.sendRedirect("success.html");
            }
        }

        else if (operation.equals("viewRecord")) {

            RestaurantBean bean = viewRecord(request);

            if (bean == null) {
                request.setAttribute("msg", "No matching records exists! Please try again!");
                RequestDispatcher rd = request.getRequestDispatcher("displayOrder.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("order", bean);
                RequestDispatcher rd = request.getRequestDispatcher("displayOrder.jsp");
                rd.forward(request, response);
            }
        }

        else if (operation.equals("viewAllRecords")) {

            List<RestaurantBean> list = viewAllRecords(request);

            if (list == null || list.isEmpty()) {
                request.setAttribute("msg", "No records available!");
                RequestDispatcher rd = request.getRequestDispatcher("displayAllOrders.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("orders", list);
                RequestDispatcher rd = request.getRequestDispatcher("displayAllOrders.jsp");
                rd.forward(request, response);
            }
        }
    }
}
