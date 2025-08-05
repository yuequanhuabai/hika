package com.e.hika.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.e.hika.pojo.School;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolMapper extends BaseMapper<School> {
    // Now, all basic CRUD methods are inherited from BaseMapper.
    // We can add custom complex queries here in the future.
}
