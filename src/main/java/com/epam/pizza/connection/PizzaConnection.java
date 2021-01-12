package com.epam.pizza.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class PizzaConnection {
    private final static Logger logger = LoggerFactory.getLogger(PizzaConnection.class);
    public static Connection getConnection() {
        try {
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/pizza");
            return ds.getConnection();
        } catch (NamingException e) {
            logger.error("Context Error: " + e);
            throw new RuntimeException("Context Error: " + e);
        } catch (SQLException e) {
            logger.error("SQL Error: " + e);
            throw new RuntimeException("SQL Error: " + e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: " + e);
                throw new RuntimeException("Error closing connection: " + e);
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: " + e);
                throw new RuntimeException("Error closing connection: " + e);
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: " + e);
                throw new RuntimeException("Error closing connection: " + e);
            }
        }
    }
}
