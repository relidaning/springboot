package com.lidaning.springboot.glance;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordMapper {

    Word findById(int id);

    List<Word> select(Word word);

    void update(Word word);

    void delete(int id);

    void insert(Word word);
}
