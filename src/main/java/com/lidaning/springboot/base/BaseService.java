package com.lidaning.springboot.base;

import org.springframework.stereotype.Service;

import java.util.List;

public interface BaseService {

    void update(Object o);

    void insert(Object o);

    Object findById(int id);

    List select(Object o);

    void delete(int id);
}
