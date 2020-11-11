package com.lidaning.springboot.glance;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpStatMapper {

    ImpStat findById();

    List<ImpStat> select(ImpStat impStat);

    void update(ImpStat impStat);

    void delete(int id);

    void insert(ImpStat impStat);
}
