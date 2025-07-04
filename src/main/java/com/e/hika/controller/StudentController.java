package com.e.hika.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.e.hika.config.ExcelListenerFactory;
import com.e.hika.listener.GenericBatchListener;
import com.e.hika.mapper.StudentMapper;
import com.e.hika.pojo.Student;
import com.e.hika.service.StudentService;
import com.e.hika.utils.ExcelUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Tag(name = "學生controller")
@RestController
@RequestMapping("/test/")
public class StudentController {

    @Resource
    private StudentMapper studentMapper;

//    @Resource
//    private IService iService;

    @Resource
    private StudentService studentService;

    @Resource
    private ExcelListenerFactory factory;

//    public StudentController(ExcelListenerFactory factory) {
//        this.factory = factory;
//    }

//    private StudentBatchHandler handler;
//
//    public StudentController(StudentBatchHandler handler) {
//        this.handler = handler;
//    }


    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Operation(summary = "分頁查詢")
    @PostMapping("query")
    public Map<String, Object> query(@RequestBody Map<String, Object> requestMap) {

//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();


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
        ExcelUtils.exportCSV(response, students, "學生列表", "學生數據", Student.class);
    }

    @GetMapping("exportStuBatch")
    public void exportStuBatch(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=student.csv");

        final int pageSize = 10_000;
        final AtomicInteger pageNo = new AtomicInteger(1);

        EasyExcel.write(response.getOutputStream(), Student.class)
                .excelType(ExcelTypeEnum.CSV)
                .charset(StandardCharsets.UTF_8)
                .autoCloseStream(false)
                .sheet("data")
                .doWrite(
                        () -> {
                            Page<Student> page = new Page<>(pageNo.getAndIncrement(), pageSize);
                            LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();

                            List<Student> students = studentMapper.selectList(page, wrapper);

                            return students.isEmpty() ? null : students;

                        }
                );
    }

    @GetMapping("exportStuBatch2")
    public void exportStuBatch2(HttpServletResponse response) {
        String filename = "student";
        String sheetName = "data";

        response.setContentType("text/csv;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".csv");

        final int pageSize = 10_000;
        final AtomicInteger pageNo = new AtomicInteger(1);


        try (ExcelWriter writer = EasyExcel.write(response.getOutputStream(), Student.class)
                .excelType(ExcelTypeEnum.CSV)
                .charset(StandardCharsets.UTF_8)
                .autoCloseStream(false)
                .build()) {
            WriteSheet sheet = EasyExcel.writerSheet(sheetName).build();

            while (true) {
                Page<Student> page = new Page<>(pageNo.getAndIncrement(), pageSize);
                LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
                List<Student> students = studentMapper.selectList(page, wrapper);
                if (students.isEmpty()) break;
                writer.write(students, sheet);
            }

            writer.finish();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importStu(@RequestParam("file") MultipartFile file) throws IOException {
        List<Student> students = ExcelUtils.importExcelMini(file, Student.class);
        for (Student student : students) {
            studentMapper.insert(student);
        }
    }


//    @PostMapping(value = "/importBatch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String importBatch(@RequestParam("file") MultipartFile file) throws IOException {
//
//        EasyExcel.read(file.getInputStream(), Student.class, new StudentCsvListener(iService))
//                .excelType(ExcelTypeEnum.CSV)
//                .charset(StandardCharsets.UTF_8)
//                .headRowNumber(1)
//                .autoCloseStream(false)
//                .sheet()
//                .doRead();
//        return "ok";
//    }


    @PostMapping(value = "/importBatch2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String importBatch2(@RequestParam("file") MultipartFile file) throws IOException {

        int batchSize = 5_000;

        GenericBatchListener<Object> objectGenericBatchListener =
                new GenericBatchListener<>(batchSize, list -> studentService.saveBatch(list, batchSize));

        EasyExcel.read(file.getInputStream(), Student.class, objectGenericBatchListener)
                .excelType(ExcelTypeEnum.CSV)
                .charset(StandardCharsets.UTF_8)
                .headRowNumber(1)
                .autoCloseStream(false)
                .sheet()
                .doRead();
        return "ok";
    }

//
//    @PostMapping(value = "/importBatch3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String importBatch3(@RequestParam("file") MultipartFile file) throws IOException {
//
//        int batchSize = 5_000;
//
//        GenericBatchListener<Student> objectGenericBatchListener =
//                new GenericBatchListener<>(batchSize, handler);
//
//        EasyExcel.read(file.getInputStream(), Student.class, objectGenericBatchListener)
//                .excelType(ExcelTypeEnum.CSV)
//                .charset(StandardCharsets.UTF_8)
//                .headRowNumber(1)
//                .autoCloseStream(false)
//                .sheet()
//                .doRead();
//        return "ok";
//    }


    @PostMapping(value = "/importBatch33", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String importBatch33(String id, MultipartFile file) throws IOException {
        logger.info("id is {}", id);

        int batchSize = 5_000;

        GenericBatchListener<Student> objectGenericBatchListener =
                factory.createBatchListener(Student.class, 5_000);

        EasyExcel.read(file.getInputStream(), Student.class, objectGenericBatchListener)
                .excelType(ExcelTypeEnum.CSV)
                .charset(StandardCharsets.UTF_8)
                .headRowNumber(1)
                .autoCloseStream(false)
                .sheet()
                .doRead();
        return "ok";
    }

    @PostMapping(value = "/importBatch4", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String importBatch4(@RequestParam("file") MultipartFile file) throws IOException {

        int batchSize = 5_000;

        GenericBatchListener<Student> objectGenericBatchListener =
                factory.createBatchListener(Student.class, 5_000);

        EasyExcel.read(file.getInputStream(), Student.class, objectGenericBatchListener)
                .excelType(ExcelTypeEnum.CSV)
                .charset(StandardCharsets.UTF_8)
                .headRowNumber(1)
                .autoCloseStream(false)
                .sheet()
                .doRead();
        return "ok";
    }

}
