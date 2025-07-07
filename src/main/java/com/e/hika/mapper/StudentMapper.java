package com.e.hika.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.e.hika.pojo.Student;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.ResultHandler;

public interface StudentMapper extends BaseMapper<Student> {

    @Select("select id,name ,s_create_time as 'sCreateTime',s_update_time as 'sUpdateTime' from student")
    @ResultType(Student.class)
    void scanAll(ResultHandler<Student> handler);
}
