package com.e.hika.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.e.hika.mapper.TeacherMapper;
import com.e.hika.pojo.Teacher;
import com.e.hika.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
