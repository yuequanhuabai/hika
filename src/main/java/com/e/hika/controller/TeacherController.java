package com.e.hika.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.e.hika.mapper.TeacherMapper;
import com.e.hika.pojo.Student;
import com.e.hika.pojo.Teacher;
import com.e.hika.utils.ExcelUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @GetMapping("export")
    public void exportTeacher(HttpServletResponse response) throws IOException {

            List<Teacher> techers = teacherMapper.selectList(new LambdaQueryWrapper<>());
        Teacher teacher = techers.get(0);
        List<Student> students = teacher.getStudents();
        ExcelUtils.exportCSV(response, techers, "教師列表", "教師數據", Teacher.class);

    }


}
