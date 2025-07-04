package com.e.hika.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.e.hika.handler.BatchHandler;
import com.e.hika.mapper.StudentMapper;
import com.e.hika.pojo.Student;
import com.e.hika.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService, BatchHandler<Student> {

    @Override
    public void handle(List<Student> batch) {
        this.saveOrUpdateBatch(batch,5_000);
    }
}
