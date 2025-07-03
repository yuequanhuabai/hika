package com.e.hika.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.e.hika.config.ExcelListenerFactory;
import com.e.hika.listener.GenericBatchListener;
import com.e.hika.mapper.TeacherMapper;
import com.e.hika.pojo.Student;
import com.e.hika.pojo.Teacher;
import com.e.hika.utils.ExcelUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "老師controller")
@RestController
@RequestMapping("/teacher/")
public class TeacherController {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private ExcelListenerFactory factory;

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


    @PostMapping(value = "importBatch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String importBatch(@RequestParam("file") MultipartFile file) throws IOException {

        int batchSize = 5_000;

        GenericBatchListener<Teacher> objectGenericBatchListener =
                factory.createBatchListener(Teacher.class, 5_000);

        EasyExcel.read(file.getInputStream(), Teacher.class, objectGenericBatchListener)
                .excelType(ExcelTypeEnum.CSV)
                .charset(StandardCharsets.UTF_8)
                .headRowNumber(1)
                .autoCloseStream(false)
                .sheet()
                .doRead();
        return "ok";
    }


}
