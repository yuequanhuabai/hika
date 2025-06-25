package com.e.hika.service;

import com.e.hika.pojo.Student;

import java.util.List;

public interface StudentService {

     List<Student> getAllStudents();

     void saveStudents(List<Student> student);
}
