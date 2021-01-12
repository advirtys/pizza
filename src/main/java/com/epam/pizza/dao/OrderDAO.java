package com.epam.pizza.dao;

import com.epam.pizza.connection.PizzaConnection;
import com.epam.pizza.entity.Address;
import com.epam.pizza.entity.Order;
import com.epam.pizza.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class OrderDAO {
    private final static Logger logger = LoggerFactory.getLogger(OrderDAO.class);
    private static String SELECT_ALL = "SELECT * FROM `pizza`.`order`";
    private String INSERT_ORDER = "INSERT INTO `pizza`.`order`(`user_id`,`address`,`description`,`count`, `date`)VALUES(?,?,?,?,?)";
    private String SELECT_BY_ID = "SELECT * FROM `pizza`.`order` WHERE user_id = ";
    private String UPDATE_STATUS = "UPDATE `pizza`.`order` SET status = ? WHERE id = ?";
    private String tmp;
    private Connection connection;

    public OrderDAO() {
       connection = PizzaConnection.getConnection();
        tmp = SELECT_ALL;
    }


    public List<Order> selectById(int id) {
        List<Order> orders = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_BY_ID + id);) {

            while (rs.next()) {
                Order order = new Order();
                order.setDescription(rs.getString("description"));
                order.setSize(rs.getInt("count"));
                order.setDate(rs.getDate("date"));
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        }
        return orders;
    }

    public List<Order> selectAll() {
        List<Order> orders = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                UserDAO userDAO = new UserDAO();
                User user = userDAO.selectById(rs.getInt("user_id"));
                order.setUser(user);

                String[] adr = rs.getString("address").split(" ");
                Address address = new Address();
                address.setAddressee(adr[0]);
                address.setStreet(adr[1]);
                address.setHouseNumber(Integer.parseInt(adr[2]));
                address.setFlatNumber(Integer.parseInt(adr[3]));
                address.setPhone(adr[4]);
                order.setAddress(address);

                order.setDate(rs.getDate("date"));
                order.setDescription(rs.getString("description"));
                order.setSize(rs.getInt("count"));
                order.setStatus(rs.getInt("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        }
        SELECT_ALL = tmp;

        return orders;
    }


    public void insertEntity(Order order) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ORDER);) {
            ps.setInt(1, order.getUser().getId());
            ps.setString(2, order.getAddress().toString());
            ps.setString(3, order.toString());
            ps.setInt(4, order.getSize());
            ps.setDate(5, order.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw  new RuntimeException("SQL error: " + e);
        }
    }



    public void close() {
        PizzaConnection.closeConnection(connection);
    }

    public void setParameter(String param) {
        if (param.equals("new")) {
            SELECT_ALL = SELECT_ALL + " WHERE date like \'" + new Date(new java.util.Date().getTime()).toString() + "\' AND status like 0";
        } else if (param.equals("delivered")) {
            SELECT_ALL = SELECT_ALL + " WHERE status like 1";
        }  else {
            SELECT_ALL = tmp;
        }
     }


    public void updateStatus(int id, int status) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS)) {
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        }
    }
}
