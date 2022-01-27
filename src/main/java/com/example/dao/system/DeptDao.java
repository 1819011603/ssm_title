package com.example.dao.system;

import com.example.domain.system.Dept;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/15  21:21
 * @VERSION 1.0
 */
public interface DeptDao {
    int save(Dept dept);
    int delete(String id);
    int update(Dept dept);
    Dept findById(String id);
    List<Dept> findAll();
    Dept findByName(String departName);
}
