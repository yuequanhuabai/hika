package com.e.hika.handler;

import com.e.hika.pojo.Student;

public class StudentHandler extends JacksonTypeHandler<Student> {
    public StudentHandler() {
        super(Student.class);
    }
}
