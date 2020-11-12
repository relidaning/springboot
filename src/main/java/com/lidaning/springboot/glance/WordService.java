package com.lidaning.springboot.glance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    @Autowired
    WordMapper wordMapper;

    Word findById(int id){
        return wordMapper.findById(id);
    }

    List<Word> select(Word word){

        return wordMapper.select(word);
    }

    void update(Word word){
        wordMapper.update(word);
    }

    void delete(int id){
        wordMapper.delete(id);
    }

    void insert(Word word){
        wordMapper.insert(word);
    }


}
