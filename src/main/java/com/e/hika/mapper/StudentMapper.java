package com.e.hika.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.e.hika.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    int insertBatch(@Param("list")List<Student> list);
}
