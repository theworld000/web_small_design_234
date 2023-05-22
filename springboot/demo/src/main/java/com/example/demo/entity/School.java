package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class School {
    @TableId(type = IdType.AUTO) //指明主键在数据库的名字
    int id;
    String schoolname;
}
