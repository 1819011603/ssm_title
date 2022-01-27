package com.example.service.system;

import com.example.domain.system.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/15  21:34
 * @VERSION 1.0
 */
public interface SysLogService {
    void save(SysLog dept);
    void delete(String id);
    void update(SysLog dept);
    SysLog findById(String id);
    List<SysLog> findAll();
    PageInfo findAll(int page, int size);
}
