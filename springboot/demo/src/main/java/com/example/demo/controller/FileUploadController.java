package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.Course;
import com.example.demo.entity.ResponseResult;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    CourseService courseService;


    @PostMapping("/add")
    public ResponseResult uploadFile(@RequestParam(value = "file", required = false)MultipartFile file, @RequestParam Integer id){
        System.out.println("start  \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        ResponseResult res = new ResponseResult();
        if(file.isEmpty())
        {
            return ResponseResult.fail();
        }

        //获得上传过来的图片名字
        String originalFilename = file.getOriginalFilename();
        //为防止命名重复 采用系统时间戳+原始文件的后缀名
        String fileName = System.currentTimeMillis() + "." + originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        //设置保存地址 （这里是转义字符）
        //1.后台保存位置
        String path = "E:\\webcodes\\web_class_small_design\\codes\\spring_vue_codes\\vue\\src\\assets\\Images\\";
        System.out.println(path+fileName);
        File dest = new File(path+fileName);
        //判断文件是否存在
        if(!dest.getParentFile().exists()){
            //如果不存在就创建一个
            dest.getParentFile().mkdirs();
        }
        try{
            //后台上传
            file.transferTo(dest);
            Course course = courseService.getById(id);
            course.setImageUrl(fileName);
            System.out.println(fileName);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("id", course.getId());
            courseService.update(course, updateWrapper);

            return new ResponseResult(200, "文件上传成功", true, fileName);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseResult.fail();
        }
    }
}
