package com.epam.pizza.entity;

import org.joda.money.Money;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Order extends BaseEntity {

    private User user;
    private Map<Product, Integer> products;
    private Address address;
    private int size = 0;
    private String description;
    private Money price = Money.parse("KZT 0.00");
    private Date date;
    private int status;

    public Order() {
        if (products == null) {
            products = new HashMap<>();
        }
    }

    public void add(Product product) {
        price = price.plus(product.getPrice());
        if (!products.containsKey(product)) {
            products.put(product, 1);
        } else {
            int count = products.get(product);
            count += 1;
            products.put(product, count);
        }
        size++;
    }

    public void remove() {
        if (products != null) {
            price = Money.parse("KZT 0.00");
            size = 0;
            products.clear();
        }
    }

    public int getSize() {
        return size;
    }

    public Money getPrice() {
        return price;
    }

    public String getPrice0() {
        return price.getAmount().toString();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            sb.append(entry.getKey().getTitle().replace("<<<>>>", "/") + " - " + entry.getValue() +"\n");
        }
        return sb.toString();
    }


}
