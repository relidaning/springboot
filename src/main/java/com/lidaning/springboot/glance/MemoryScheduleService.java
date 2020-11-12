package com.lidaning.springboot.glance;

import com.lidaning.springboot.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemoryScheduleService implements BaseService {

    @Autowired
    MemoryScheduleMapper memoryScheduleMapper;

    @Override
    public void update(Object o) {

    }

    @Override
    public void insert(Object o) {

    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List select(Object o) {
        return memoryScheduleMapper.select(o);
    }

    @Override
    public void delete(int id) {

    }

    public MemorySchedule get(){
        return memoryScheduleMapper.get();
    }

}
