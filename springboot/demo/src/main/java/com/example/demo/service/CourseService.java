package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Course;
import com.example.demo.mapper.CourseMapper;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends ServiceImpl<CourseMapper, Course> {

}

