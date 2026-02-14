package com.wipro.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.restaurant.bean.RestaurantBean;
import com.wipro.restaurant.util.DBUtil;

public class RestaurantDAO {

    public String createRecord(RestaurantBean restaurantBean) {

        Connection connection = DBUtil.getDBConnection();
        String query = "INSERT INTO RESTAURANT_TB VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, restaurantBean.getRecordId());
            ps.setString(2, restaurantBean.getCustomerName());
            ps.setString(3, restaurantBean.getFoodItem());
            ps.setInt(4, restaurantBean.getQuantity());
            ps.setDate(5, new java.sql.Date(restaurantBean.getOrderDate().getTime()));
            ps.setInt(6, restaurantBean.getTotalAmount());
            ps.setString(7, restaurantBean.getRemarks());

            int row = ps.executeUpdate();
            if (row > 0) {
                return restaurantBean.getRecordId();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "FAIL";
    }

    public RestaurantBean fetchRecord(String customerName, Date orderDate) {

        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM RESTAURANT_TB WHERE CUSTOMERNAME=? AND ORDER_DATE=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customerName);
            ps.setDate(2, new java.sql.Date(orderDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                RestaurantBean bean = new RestaurantBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setFoodItem(rs.getString("FOODITEM"));
                bean.setQuantity(rs.getInt("QUANTITY"));
                bean.setOrderDate(rs.getDate("ORDER_DATE"));
                bean.setTotalAmount(rs.getInt("TOTALAMOUNT"));
                bean.setRemarks(rs.getString("REMARKS"));

                return bean;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String generateRecordID(String customerName, Date orderDate) {

        String id = "";
        try {
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            String temp = format.format(orderDate);

            String prefix = customerName.substring(0, 2).toUpperCase();

            Connection connection = DBUtil.getDBConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT RESTAURANT_SEQ.NEXTVAL FROM DUAL");

            int seq = 0;
            if (rs.next()) {
                seq = rs.getInt(1);
            }

            id = temp + prefix + String.format("%02d", seq);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean recordExists(String customerName, Date orderDate) {

        RestaurantBean bean = fetchRecord(customerName, orderDate);
        if (bean != null) {
            return true;
        }
        return false;
    }

    public List<RestaurantBean> fetchAllRecords() {

        List<RestaurantBean> list = new ArrayList<>();

        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT * FROM RESTAURANT_TB";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                RestaurantBean bean = new RestaurantBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setFoodItem(rs.getString("FOODITEM"));
                bean.setQuantity(rs.getInt("QUANTITY"));
                bean.setOrderDate(rs.getDate("ORDER_DATE"));
                bean.setTotalAmount(rs.getInt("TOTALAMOUNT"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
