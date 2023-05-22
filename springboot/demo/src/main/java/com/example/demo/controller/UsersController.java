package com.example.demo.controller;


import com.example.demo.entity.Users;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/login")
    public int login(@RequestParam(defaultValue = "") String username,
                     @RequestParam(defaultValue = "") String password) {
        List<Users> list = usersService.list();
        System.out.println("username  a " + username);
        System.out.println("password  b " + password);
        for (int i = 0; i < list.size(); i++) {
            if (username.equals(list.get(i).getName()) && password.equals(list.get(i).getPassword()))
                return 200;
        }
        return 201;
    }

    @GetMapping("/add")
    public boolean add(@RequestParam String name,
                       @RequestParam String password,
                       @RequestParam String firstName){
        Users users = new Users();
        users.setName(name);
        users.setPassword(password);
        users.setFirstName(firstName);
        return usersService.saveOrUpdate(users);
    }
}
