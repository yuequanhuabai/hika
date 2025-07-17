package com.e.hika.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.e.hika.mapper.ClassesMapper;
import com.e.hika.pojo.Classes;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classes/")
public class ClassesController {

    @Resource
    private ClassesMapper classesMapper;

    @PostMapping("query")
    public String test1() {
        Integer pageCurrent = 1;
        Integer pageSize = 10;
        LambdaQueryWrapper<Classes> classesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Classes> page = new Page<>(pageCurrent, pageSize);

        List<Classes> classes = classesMapper.selectList(page, classesLambdaQueryWrapper);
        System.out.println(classes);

        return "";
    }


}
