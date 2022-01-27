package com.example.dao.store;


import com.example.domain.store.Course;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/16  19:03
 * @VERSION 1.0
 */
public interface CourseDao {
    int save(Course course);
    int delete(String id);
    int update(Course course);
    Course findById(String id);
    List<Course> findAll();
}
