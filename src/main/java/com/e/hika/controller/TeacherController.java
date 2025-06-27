package com.e.hika.controller;

import com.e.hika.pojo.Teacher;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "老師controller")
@RestController
@RequestMapping("/teacher/")
public class TeacherController {

    public List<Teacher> query() {
        List<Teacher> list = new ArrayList<Teacher>();
        return list;
    }


}
