package com.e.hika.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.e.hika.mapper.StudentMapper;
import com.e.hika.pojo.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentCsvListener extends AnalysisEventListener<Student> {
    private static final int BATCH_SIZE = 5000;

    private final List<Student> cache = new ArrayList<>(BATCH_SIZE);

    private final StudentMapper studentMapper;
    public StudentCsvListener(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }


    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
          cache.add(student);
          if(cache.size() >= BATCH_SIZE) {
              studentMapper.insertBatch(cache);
              cache.clear();
          }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if(!cache.isEmpty()){
            studentMapper.insertBatch(cache);
        }
    }
}
