package com.example.service.system;

import com.example.domain.system.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/15  21:34
 * @VERSION 1.0
 */
public interface DeptService {
    void save(Dept dept);
    void delete(String id);
    void update(Dept dept);
    Dept findById(String id);
    List<Dept> findAll();
    PageInfo findAll(int page, int size);
    Dept findByName(String parent);
}
