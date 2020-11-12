package com.lidaning.springboot.glance;

import com.lidaning.springboot.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryInfoService implements BaseService {

    @Autowired
    MemoryInfoMapper memoryInfoMapper;
    @Override
    public void update(Object o) {
        memoryInfoMapper.update(o);
    }

    @Override
    public void insert(Object o) {
        memoryInfoMapper.insert(o);
    }

    @Override
    public Object findById(int id) {
        return memoryInfoMapper.findById(id);
    }

    @Override
    public List select(Object o) {
        return memoryInfoMapper.select(o);
    }

    @Override
    public void delete(int id) {
        memoryInfoMapper.delete(id);
    }
}
