package com.lidaning.springboot.glance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpStatService {

    @Autowired
    ImpStatMapper impStatMapper;

    ImpStat findById(){
        return impStatMapper.findById();
    }

    List<ImpStat> select(ImpStat ImpStat){

        return impStatMapper.select(ImpStat);
    }

    void update(ImpStat ImpStat){
        impStatMapper.update(ImpStat);
    }

    void delete(int id){
        impStatMapper.delete(id);
    }

    void insert(ImpStat ImpStat){
        impStatMapper.insert(ImpStat);
    }
}
