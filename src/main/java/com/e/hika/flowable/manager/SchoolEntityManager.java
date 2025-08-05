package com.e.hika.flowable.manager;

import com.e.hika.pojo.School;

import java.util.List;

public interface SchoolEntityManager {
    School findById(String id);
    List<School> findAll();
    void insert(School school);
    void update(School school);
    void delete(String id);
}
