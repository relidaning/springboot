package com.lidaning.springboot.glance;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WordMapper {

    @Select("select * from word")
    List<Word> getAll();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "word", column = "word"),
            @Result(property = "pronunciation", column = "pronunciation"),
            @Result(property = "description", column = "description"),
            @Result(property = "impDate", column = "impDate")
    })

    @Select("select * from word where id=#{id}")
    Word getById(Long id);

    /*@Select("select * from user where username like concat('%',#{name},'%')")
    List<Word> getUsersByName(String name);

    @Insert({"insert into user(username,address) values(#{username},#{address})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    Integer addUser(Word w);

    @Update("update user set username=#{username},address=#{address} where id=#{id}")
    Integer updateUserById(Word user);*/

    @Delete("delete from user where id=#{id}")
    Integer deleteUserById(Integer id);

}
