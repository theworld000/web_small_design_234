package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Course;
import com.example.demo.entity.School;
import com.example.demo.service.CourseService;
import com.example.demo.service.SchoolService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private SchoolService schoolService;

    @GetMapping("")
    public List<Course> index(){
        return courseService.list();
    }

    @GetMapping("/page")
    public IPage<Course> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String id,
                                  @RequestParam(defaultValue = "") String hours,
                                  @RequestParam(defaultValue = "") String sid) {
        IPage<Course> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Course> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like(Strings.isNotEmpty(id), "id", id);
        userQueryWrapper.like(Strings.isNotEmpty(hours), "hours", hours);
        userQueryWrapper.like(Strings.isNotEmpty(sid), "sid", sid);
        userQueryWrapper.orderByDesc("id");
        return courseService.page(page, userQueryWrapper);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return courseService.removeById(id);
    }

    //新增批量删除接口
    @DeleteMapping("/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        System.out.println(ids);
        return courseService.removeBatchByIds(ids);
    }

    @PostMapping("") //  可以把前台传来的数据 json转变为java对象
    public boolean save(@RequestBody Course course) {
        //新增 或者 更新
        System.out.println("aaaa");
        System.out.println(course);

        return courseService.saveOrUpdate(course);
    }

    @PostMapping("/check")
    public void init(){
        List<Course> list = courseService.list();
        for(int i=0; i<list.size(); i++)
        {
            int sid = list.get(i).getSid();
            School school = schoolService.getById(sid);
            if(school != null) list.get(i).setSname(school.getSchoolname());
            else list.get(i).setSname("学院未命名");

            String imageUrl = list.get(i).getImageUrl();
            System.out.println("url == " + imageUrl);
            if(imageUrl == null){
                list.get(i).setImageUrl("initial.png");
            }

            save(list.get(i));
            System.out.println("save" + list.get(i));
        }
    }
}
