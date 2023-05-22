package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Course;
import com.example.demo.entity.School;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolMapper extends BaseMapper<School> {

}
