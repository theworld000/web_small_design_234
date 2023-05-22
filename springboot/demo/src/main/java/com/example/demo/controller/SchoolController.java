package com.example.demo.controller;

import com.example.demo.entity.School;
import com.example.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/{id}")
    public String getNameById(@PathVariable Integer id) {
        School school = schoolService.getById(id);
        System.out.println("here aaa bbb ccc \n\n\n\n");
        System.out.println(school);
        if(school!=null) return school.getSchoolname();
        return "学院未命名";
    }
}
