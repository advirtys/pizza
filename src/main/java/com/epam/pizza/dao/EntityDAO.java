package com.epam.pizza.dao;

import com.epam.pizza.entity.User;
import com.epam.pizza.service.exception.ServiceException;

import java.util.List;

public interface EntityDAO<T> {

    List<T> selectAll();
    T selectById(int id);
    void insertEntity(T t);
    T findByEntity(T t);
    void updateEntity(T t);
    void deleteEntity(int id);
    void close();
}
