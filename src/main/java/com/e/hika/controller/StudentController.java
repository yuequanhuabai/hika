package com.e.hika.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.e.hika.mapper.StudentMapper;
import com.e.hika.pojo.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "學生controller")
@RestController
@RequestMapping("/test/")
public class StudentController {

    @Resource
    private StudentMapper studentMapper;


    @Operation(summary = "分頁查詢")
    @RequestMapping("query")
    public Map<String, Object> query(@RequestBody Map<String, Object> requestMap) {

        Integer pageCurrent = (Integer) requestMap.get("pageCurrent");

        Integer pageSize = (Integer) requestMap.get("pageSize");

        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }


        Page<Student> page = new Page<>(pageCurrent, pageSize);

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(true,Student::getId, Student::getName);

        Page<Student> studentPage = studentMapper.selectPage(page, wrapper);
        List<Student> records = studentPage.getRecords();

        long total = studentPage.getTotal();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);


        return map;
    }
}
