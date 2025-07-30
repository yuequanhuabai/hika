package com.e.hika.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.e.hika.config.ExcelListenerFactory;
import com.e.hika.handler.CsvWritingHandler;
import com.e.hika.listener.GenericBatchListener;
import com.e.hika.mapper.StudentMapper;
import com.e.hika.pojo.Student;
import com.e.hika.service.StudentService;
import com.e.hika.utils.ExcelUtils;
import com.opencsv.CSVWriter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.cursor.Cursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Tag(name = "學生controller")
@RestController
@RequestMapping("/stu/")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Value("${user.id:v02906}")
    private String code;

    //    @Resource
//    private IService iService;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentService studentService;

//    @Resource
//    private CsvWritingHandler csvWritingHandler;

//    public StudentController(ExcelListenerFactory factory) {
//        this.factory = factory;
//    }

    //    private StudentBatchHandler handler;
//
//    public StudentController(StudentBatchHandler handler) {
//        this.handler = handler;
//    }
    @Resource
    private ExcelListenerFactory factory;

    @Operation(summary = "分頁查詢")
    @PostMapping("query")
    public Map<String, Object> query(@RequestBody Map<String, Object> requestMap) {

//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

        System.out.println("code: "+code);
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


    @Operation(summary = "分頁查詢")
    @PostMapping("update")
    public int update(@RequestBody Student student) {

        List<String> ids = student.getIds();

//        8bc85555-612a-11f0-ad43-588a5a42d312
//        8bc9ca7c-612a-11f0-ad43-588a5a42d312

        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.in(Student::getId, ids);
        studentLambdaQueryWrapper.eq(Student::getName, student.getName());

        Student s = new Student();
        s.setsUpdateTime(LocalDateTime.now());
        s.setName("zhangsan111");

        int update = studentMapper.update(s, studentLambdaQueryWrapper);


        return update;
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


    @GetMapping("exportStuBatch3")
    public void exportStuBatch3(HttpServletResponse response) {
//        String filename = "student-batch.csv";
//       Path out = Paths.get(filename);
//        try(CsvWritingHandler handler = new CsvWritingHandler(out)){
//            studentMapper.scanAll(handler);
//        }

        // 1) 設定響應頭
        response.setContentType("text/csv;charset=UTF-8");
//        response.setContentType("text/tab-separated-values;charset=utf-8");
//        // filename 需 URL-encode，避免中文/空格亂碼
        String filename = URLEncoder.encode("student-batch.csv", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

//        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));
//             CsvWritingHandler handler = new CsvWritingHandler(bufferedWriter)
//        ) {
//            LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
//            studentMapper.selectList(wrapper, handler);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        // 2) 直接把 CSV 流寫進 response
        try (BufferedWriter writer =
                     new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));
             CsvWritingHandler handler = new CsvWritingHandler(writer)) {
            LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
            studentMapper.selectList(wrapper, handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @GetMapping(value = "exportStuBatch4", produces = "text/csv")
    @Transactional(readOnly = true)
    public void exportStuBatch4(HttpServletResponse response) {

        // 1. 设定一个响应头--告诉浏览器这是个附件
        String fileName = "student_cursor" + ".csv";
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", "attachment;filename=\"" +
                URLEncoder.encode(fileName, StandardCharsets.UTF_8) + "\"");

        // 2. 计算查询区间；就是查询条件；如果有；

        // 3. 获取Servlet输出流；
        try (
                ServletOutputStream outputStream = response.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                CSVWriter csvWriter = new CSVWriter(outputStreamWriter,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.NO_ESCAPE_CHARACTER,
                        System.lineSeparator()
                )
        ) {
            Cursor<Student> students = studentMapper.scanStudentCursor();
            csvWriter.writeNext(new String[]{
                    "id", "name", "s_create_time", "s_update_time"
            });
            for (Student s : students) {
                csvWriter.writeNext(new String[]{
                        s.getId().toString(),
                        s.getName().toString(),
                        s.getsCreateTime().toString(),
                        s.getsUpdateTime().toString()
                });
            }

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
