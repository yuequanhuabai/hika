package com.e.hika.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.e.hika.converter.StudentConverter;
import com.google.gson.Gson;

import java.time.LocalDateTime;
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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Student> students;


    @ExcelProperty("創建時間")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tCreateTime;

    @ExcelProperty("更新時間")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tUpdateTime;

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

    public LocalDateTime gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(LocalDateTime tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public LocalDateTime gettUpdateTime() {
        return tUpdateTime;
    }

    public void settUpdateTime(LocalDateTime tUpdateTime) {
        this.tUpdateTime = tUpdateTime;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                ", tCreateTime=" + tCreateTime +
                ", tUpdateTime=" + tUpdateTime +
                '}';
    }
}
