package com.e.hika.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.e.hika.converter.GenericListConverter;
import com.e.hika.converter.StudentConverter;
import com.e.hika.handler.JsonListTypeHandler;
import com.google.gson.Gson;

import java.util.List;


@TableName("teacher")
public class Teacher {

    private static final Gson gson = new Gson();

    @ExcelProperty("教師Id")
    private Long id;

    @ExcelProperty("教師姓名")
    private String name;

    //    @TableField(typeHandler = JsonListTypeHandler.class)
    @ExcelProperty(value = "教師的學生", converter = StudentConverter.class)
    @TableField(typeHandler = Fastjson2TypeHandler.class)
    private List<Student> students;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //    public List<Student> getStudents() {
//        if (students == null || students.isEmpty()) {
//            return new ArrayList<>();
//        }
//        return gson.fromJson(students, new TypeToken<List<Student>>() {
//        }.getType());
//
//    }

//    public String getStudents() {
//        return students;
//    }

//    public void setStudents(String students) {
//        this.students = students;
//    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
