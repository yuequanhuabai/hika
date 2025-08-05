package com.e.hika.flowable.manager;

import com.e.hika.mapper.SchoolMapper;
import com.e.hika.pojo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchoolEntityManagerImpl implements SchoolEntityManager {

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public School findById(String id) {
        return schoolMapper.selectById(id);
    }

    @Override
    public List<School> findAll() {
        return schoolMapper.selectList(null);
    }

    @Override
    public void insert(School school) {
        schoolMapper.insert(school);
    }

    @Override
    public void update(School school) {
        schoolMapper.updateById(school);
    }

    @Override
    public void delete(String id) {
        schoolMapper.deleteById(id);
    }
}
