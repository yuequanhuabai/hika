package com.e.hika.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("teacher")
public class Teacher {
    private Long id;
    private String name;
    private List<Student> students;
}
