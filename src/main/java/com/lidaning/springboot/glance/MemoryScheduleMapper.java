package com.lidaning.springboot.glance;

import com.lidaning.springboot.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryScheduleMapper extends BaseMapper {

    MemorySchedule get();
}
