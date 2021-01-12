package com.epam.pizza.dao;

import com.epam.pizza.connection.PizzaConnection;
import com.epam.pizza.entity.Product;
import com.epam.pizza.entity.User;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements EntityDAO<Product> {
    private String locale;
    private InputStream img;
    private Connection connection = PizzaConnection.getConnection();
    private final static Logger logger = LoggerFactory.getLogger(ProductDAO.class);
    private final String UPDATE_PRODUCT = "UPDATE product SET title_ru_RU = ?, title_en_US = ?, description_ru_RU = ?, description_en_US = ?, price = ?, type = ?, img = ? WHERE id = ?";
    private final String UPDATE_IMG = "UPDATE product SET img = ? WHERE id = ?";
    private final String SELECT_ALL_PRODUCT = "SELECT * FROM product";
    private final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
    private final String INSERT_PRODUCT = "INSERT INTO product(title_ru_RU, title_en_US, description_ru_RU, description_en_US, price, type, img) VALUES (?,?,?,?,?,?,?)";
    private final String SELECT_IMG_BY_ID = "SELECT img FROM product WHERE id =";
    private final String SELECT_BY_ID = "SELECT * FROM product WHERE id = ";

    public ProductDAO() {
    }

    public ProductDAO(InputStream img) {
        this.img = img;
    }

    public ProductDAO(String locale) {
        this.locale = locale;
    }

    @Override
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_PRODUCT)) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title_" + locale));
                product.setDescription(rs.getString("description_" + locale));
                product.setPrice(Money.parse("KZT "+ rs.getString("price")));
                product.setType(Product.Type.valueOf(rs.getString("type")));
                products.add(product);
            }

        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        } finally {
            PizzaConnection.closeConnection(connection);
        }
        return products;
    }

    @Override
    public void updateEntity(Product product) {
        String[] titles = product.getTitle().split(" <<<>>> ");
        String[] desc = product.getDescription().split(" <<<>>> ");
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT)) {
            ps.setString(1, titles[0]);
            ps.setString(2, titles[1]);
            ps.setString(3, desc[0]);
            ps.setString(4, desc[1]);
            ps.setString(5, product.getPrice0());
            ps.setString(6, product.getType().toString());
            ps.setBinaryStream(7, img);
            ps.setInt(8, product.getId());
            ps.execute();

        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        }
    }

    @Override
    public Product selectById(int id) {
        Product product = new Product();

            try (Statement st = connection.createStatement();
                 ResultSet rs = st.executeQuery(SELECT_BY_ID + id)) {
                    while (rs.next()) {
                        product.setId(rs.getInt("id"));
                        product.setTitle(rs.getString("title_ru_RU") + " <<<>>> " + rs.getString("title_en_US"));
                        product.setDescription(rs.getString("description_ru_RU") + " <<<>>> " + rs.getString("description_en_US"));
                        product.setType(Product.Type.valueOf(rs.getString("type")));
                        product.setPrice(Money.parse("KZT " + rs.getString("price")));
                    }
            } catch (SQLException e) {
                logger.error("SQL error:" + e);
                throw new RuntimeException("SQL error: " + e);
            }

        return product;
    }



    @Override
    public Product findByEntity(Product product) {
        return null;
    }

    @Override
    public void insertEntity(Product product) {
        String[] titles = product.getTitle().split(" <<<>>> ");
        String[] desc = product.getDescription().split(" <<<>>> ");

        try (PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT);) {
            ps.setString(1, titles[0]);
            ps.setString(2, titles[1]);
            ps.setString(3, desc[0]);
            ps.setString(4, desc[1]);
            ps.setString(5, product.getPrice0());
            ps.setString(6, product.getType().toString());
            ps.setBinaryStream(7, img);
            ps.execute();

        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        }
    }

    public InputStream getImage(int id) {
        InputStream is = null;
        try (Connection connection = PizzaConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_IMG_BY_ID + id)) {
            while (rs.next()) {
                is = rs.getBinaryStream("img");
            }

        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        }
        return is;
    }

    @Override
    public void deleteEntity(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT);) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error("SQL error:" + e);
            throw new RuntimeException("SQL error: " + e);
        } finally {
            PizzaConnection.closeConnection(connection);
        }
    }

    @Override
    public void close() {
        PizzaConnection.closeConnection(connection);
    }


}
