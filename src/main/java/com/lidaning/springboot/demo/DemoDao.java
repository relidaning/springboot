package com.lidaning.springboot.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoDao extends JpaRepository<Demo, Long> {

    Demo findByName(String name);
}
