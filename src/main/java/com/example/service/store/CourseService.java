package com.example.service.store;


import com.example.domain.store.Course;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/14  21:50
 * @VERSION 1.0
 */
public interface CourseService {

    void save(Course company);
    void delete(String id);
    void update(Course company);
    Course findById(String id);
    List<Course> findAll();
    PageInfo findAll(int page, int size);
}
