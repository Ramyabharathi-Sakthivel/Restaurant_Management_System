package com.wipro.restaurant.service;

import java.util.Date;
import java.util.List;

import com.wipro.restaurant.bean.RestaurantBean;
import com.wipro.restaurant.dao.RestaurantDAO;
import com.wipro.restaurant.util.InvalidInputException;

public class Administrator {

    public String addRecord(RestaurantBean restaurantBean) {

        try {

            if (restaurantBean == null ||
                restaurantBean.getCustomerName() == null ||
                restaurantBean.getOrderDate() == null) {
                throw new InvalidInputException();
            }

            if (restaurantBean.getCustomerName().length() < 2) {
                return "INVALID CUSTOMER NAME";
            }

            RestaurantDAO dao = new RestaurantDAO();

            if (dao.recordExists(
                    restaurantBean.getCustomerName(),
                    restaurantBean.getOrderDate())) {
                return "ALREADY EXISTS";
            }

            String id = dao.generateRecordID(
                    restaurantBean.getCustomerName(),
                    restaurantBean.getOrderDate());

            restaurantBean.setRecordId(id);

            return dao.createRecord(restaurantBean);

        } catch (Exception e) {
            return "INVALID INPUT";
        }
    }

    public RestaurantBean viewRecord(String customerName, Date orderDate) {

        RestaurantDAO dao = new RestaurantDAO();
        return dao.fetchRecord(customerName, orderDate);
    }

    public List<RestaurantBean> viewAllRecords() {

        RestaurantDAO dao = new RestaurantDAO();
        return dao.fetchAllRecords();
    }
}
