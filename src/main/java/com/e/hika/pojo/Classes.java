package com.e.hika.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.e.hika.handler.NameTypeHandler;
import com.e.hika.handler.StudentHandler;
import com.e.hika.handler.TeacherHandler;
import com.e.hika.i18npojo.Name;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("classes")
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

//    @TableField(value = "name", typeHandler = NameReturnHandler.class)
//@TableField(value = "name", typeHandler = NameReturnHandler.class)
@TableField(value = "name", typeHandler = NameTypeHandler.class)

    private Name name;

@TableField(value = "teacher" ,typeHandler = TeacherHandler.class)
    private Teacher teacher;


    @TableField(typeHandler = StudentHandler.class)
    private Student student;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime updateTime;
}
