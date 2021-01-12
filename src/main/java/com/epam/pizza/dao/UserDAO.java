package com.epam.pizza.dao;

import com.epam.pizza.connection.PizzaConnection;
import com.epam.pizza.entity.Product;
import com.epam.pizza.entity.User;
import com.epam.pizza.service.exception.ServiceException;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements EntityDAO<User> {
    private Connection connection = PizzaConnection.getConnection();
    private final static Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private final String SELECT_USER_BY_ID = "SELECT u.id, u.login, u.password, u.email, ur.role FROM user u INNER JOIN user_role ur WHERE ur.id = u.user_role AND u.id = ?";
    private final String SELECT_ALL_USERS = "SELECT u.id, u.login, u.email, u.password, ur.role FROM user u INNER JOIN user_role ur WHERE ur.id = u.user_role";
    private final String INSERT_USER = "INSERT INTO user(login, password, email, user_role) VALUES (?, ?, ?, ?)";
    private final String UPDATE_USER = "UPDATE user SET password=?, email=?, user_role=(SELECT id FROM user_role WHERE role=?) WHERE id = ?";
    private final String DELETE_USER = "DELETE FROM user WHERE id = ?";
    private final String FIND_USER = "SELECT u.id, u.login, u.password, u.email, ur.role FROM user u INNER" +
            " JOIN user_role ur WHERE ur.id = u.user_role AND u.login LIKE ?";


    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_USERS)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("u.id"));
                user.setLogin(rs.getString("u.login"));
                user.setEmail(rs.getString("u.email"));
                user.setPassword(rs.getString("u.password"));
                user.setRole(rs.getString("ur.role"));
                users.add(user);
            }

        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        } finally {
            PizzaConnection.closeConnection(connection);
        }
        return users;
    }
    @Override
    public User selectById(int id) {
        User user = new User();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("u.id"));
                user.setLogin(rs.getString("u.login"));
                user.setEmail(rs.getString("u.email"));
                user.setPassword(rs.getString("u.password"));
                user.setRole(rs.getString("ur.role"));
            }
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        } finally {
            PizzaConnection.closeResultSet(rs);
            PizzaConnection.closeStatement(preparedStatement);
            PizzaConnection.closeConnection(connection);
        }
        return user;
    }

    @Override
    public void insertEntity(User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, 2);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        } finally {
            PizzaConnection.closeStatement(preparedStatement);
            PizzaConnection.closeConnection(connection);
        }
    }

    @Override
    public User findByEntity(User user) {
        User result = new User();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER);
            preparedStatement.setString(1, user.getLogin());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setLogin(resultSet.getString("login"));
                result.setPassword(resultSet.getString("password"));
                result.setEmail(resultSet.getString("email"));
                result.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        } finally {
            PizzaConnection.closeResultSet(resultSet);
            PizzaConnection.closeStatement(preparedStatement);
            PizzaConnection.closeConnection(connection);
        }

        return result;
    }

    @Override
    public void updateEntity(User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        } finally {
            PizzaConnection.closeStatement(preparedStatement);
            PizzaConnection.closeConnection(connection);
        }
    }

    @Override
    public void deleteEntity(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        } finally {
            PizzaConnection.closeStatement(preparedStatement);
            PizzaConnection.closeConnection(connection);
        }
    }

    @Override
    public void close() {
        PizzaConnection.closeConnection(connection);
    }


}
