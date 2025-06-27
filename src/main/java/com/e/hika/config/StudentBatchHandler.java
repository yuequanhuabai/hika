package com.e.hika.config;

import com.e.hika.BatchHandler;
import com.e.hika.pojo.Student;
import com.e.hika.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentBatchHandler implements BatchHandler<Student> {

    private final StudentService studentService;
    public StudentBatchHandler(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void handle(List<Student> batch) {
        studentService.saveOrUpdateBatch(batch,5_000);
//        studentService.saveBatch(batch,5_000);
    }
}
