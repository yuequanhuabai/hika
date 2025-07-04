package com.e.hika.pojo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("student")
public class Student {

    @ExcelProperty("主鍵Id")
    private String id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("創建時間")
    private LocalDateTime sCreateTime;

    @ExcelProperty("更新時間")
    private LocalDateTime sUpdateTime;
}
