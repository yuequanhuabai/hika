package com.e.hika.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.e.hika.converter.StudentConverter;
import com.google.gson.Gson;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
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


    @ExcelProperty("創建時間")
    private LocalDateTime tCreateTime;

    @ExcelProperty("更新時間")
    private LocalDateTime tUpdateTime;


}
