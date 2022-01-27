package com.example.dao.system;

import com.example.domain.system.SysLog;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/15  21:21
 * @VERSION 1.0
 */
public interface SysLogDao {
    int save(SysLog sysLog);
    int delete(String id);
    int update(SysLog sysLog);
    SysLog findById(String id);
    List<SysLog> findAll();



    List<SysLog> findByName(String userName);
}
