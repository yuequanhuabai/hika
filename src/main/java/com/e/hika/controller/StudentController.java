package com.e.hika.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.e.hika.mapper.StudentMapper;
import com.e.hika.pojo.Student;
import com.e.hika.utils.ExcelUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();


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
        wrapper.orderByAsc(true, Student::getId, Student::getName);

        Page<Student> studentPage = studentMapper.selectPage(page, wrapper);
        List<Student> records = studentPage.getRecords();

        long total = studentPage.getTotal();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);


        return map;
    }

    @GetMapping("export")
    public void exportStu(HttpServletResponse response) throws IOException {
        List<Student> students = studentMapper.selectList(new LambdaQueryWrapper<>());
        ExcelUtils.exportExcel(response,students,"學生列表","學生數據",Student.class);
    }


    @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importStu(@RequestParam("file") MultipartFile file) throws IOException {

        List<Student> students = ExcelUtils.importExcel(file, Student.class);
        for (Student student : students) {
            studentMapper.insert(student);
        }


    }


}
