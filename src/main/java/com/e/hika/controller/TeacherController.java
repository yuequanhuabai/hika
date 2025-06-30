package com.e.hika.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.e.hika.mapper.TeacherMapper;
import com.e.hika.pojo.Teacher;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "老師controller")
@RestController
@RequestMapping("/teacher/")
public class TeacherController {

    @Resource
    private TeacherMapper teacherMapper;

    @RequestMapping("query")
    public List<Teacher> query() {
        List<Teacher> list = new ArrayList<Teacher>();
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Teacher> page = new Page<>(1, 10);
        List<Teacher> teachers = teacherMapper.selectList(page, teacherLambdaQueryWrapper);
        return list;
    }


}
