package com.lidaning.springboot.base;

import java.util.List;

public interface BaseMapper  {

    void update(Object o);

    void insert(Object o);

    Object findById(int id);

    List select(Object o);

    void delete(int id);
}
