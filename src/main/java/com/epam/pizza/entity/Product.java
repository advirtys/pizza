package com.epam.pizza.entity;

import org.joda.money.Money;


public class Product extends BaseEntity {
    private String title;
    private String description;
    private Money price;
    private Type type;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getPrice() {
        return price;
    }

    public String getPrice0() {
        return price.getAmount().toString();
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }




    public static enum  Type {
        PIZZA("pizza"), SUSHI("sushi"), DRINK("drink");

        String name;
        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name.toUpperCase();
        }
    }



}
