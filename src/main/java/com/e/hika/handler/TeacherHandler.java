package com.e.hika.handler;

import com.e.hika.pojo.Teacher;

public class TeacherHandler extends JacksonTypeHandler<Teacher> {
    public TeacherHandler() {
        super(Teacher.class);
    }
}
