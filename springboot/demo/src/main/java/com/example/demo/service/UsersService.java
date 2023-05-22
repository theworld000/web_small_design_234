package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends ServiceImpl<UsersMapper, Users> {

}
