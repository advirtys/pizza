package com.epam.pizza.dao;

import com.epam.pizza.connection.PizzaConnection;
import com.epam.pizza.entity.BankAccount;
import com.epam.pizza.entity.BaseEntity;
import com.epam.pizza.entity.Order;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class BankDAO extends BaseEntity {
    private final static Logger logger = LoggerFactory.getLogger(BankDAO.class);
    private String BANK_OPERATION = "UPDATE bank SET money = ? WHERE id = ?";
    private Connection connection = PizzaConnection.getConnection();

    public BankAccount findBankAccountAccount(BankAccount account) {
        BankAccount res = new BankAccount();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT * FROM bank WHERE number = "
                             + account.getNumber()
                             + " and time = "
                             + account.getTime()
                             + " and cvv = "
                             + account.getCvv())) {

            while (rs.next()) {
                res.setId(rs.getInt("id"));
                res.setNumber(rs.getString("number"));
                res.setTime(rs.getString("time"));
                res.setCvv(rs.getString("cvv"));
                res.setMoney(Money.parse("KZT " + rs.getString("money")));
            }
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error:" + e);
        }
        return res;
    }

    public void transaction(BankAccount userAccount, BankAccount pizzaAccount, Order order) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement userAccountMinus = connection.prepareStatement(BANK_OPERATION);
            Money minus = userAccount.getMoney().minus(order.getPrice());
            userAccountMinus.setString(1, minus.getAmount().toString());
            userAccountMinus.setInt(2, userAccount.getId());
            userAccountMinus.executeUpdate();

            PreparedStatement pizzaAccountPlus = connection.prepareStatement(BANK_OPERATION);
            Money plus = pizzaAccount.getMoney().plus(order.getPrice());
            pizzaAccountPlus.setString(1, plus.getAmount().toString());
            pizzaAccountPlus.setInt(2, pizzaAccount.getId());
            pizzaAccountPlus.executeUpdate();

            OrderDAO orderDAO = new OrderDAO();
            orderDAO.insertEntity(order);
            orderDAO.close();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error("SQL Rollback:" + e1);
                throw new RuntimeException("Rollback: " + e);
            }
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        }
    }

    public void close() {
        PizzaConnection.closeConnection(connection);
    }


}
