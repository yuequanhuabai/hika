package com.e.hika.pojo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

@TableName("student")
public class Student {

    @ExcelProperty("主鍵Id")
    private String id;

    @ExcelProperty("姓名")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @ExcelProperty("創建時間")
    private LocalDateTime sCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @ExcelProperty("更新時間")
    private LocalDateTime sUpdateTime;

    // 接受前端參數，不返回;
    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private transient String at;

    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getsCreateTime() {
        return sCreateTime;
    }

    public void setsCreateTime(LocalDateTime sCreateTime) {
        this.sCreateTime = sCreateTime;
    }

    public LocalDateTime getsUpdateTime() {
        return sUpdateTime;
    }

    public void setsUpdateTime(LocalDateTime sUpdateTime) {
        this.sUpdateTime = sUpdateTime;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sCreateTime=" + sCreateTime +
                ", sUpdateTime=" + sUpdateTime +
                ", at='" + at + '\'' +
                '}';
    }
}
