package com.lidaning.springboot.demo;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
public class Demo implements Serializable {
    @javax.persistence.Id
    @Id@GeneratedValue
    private Long id;
    private String name;
    private int age;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


}
