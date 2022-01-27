package com.example.dao.store;

import com.example.domain.store.ExamineLog;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/22  18:38
 * @VERSION 1.0
 */
public interface ExamineLogDao {
    int save(ExamineLog sysLog);
    int delete(String id);
    int update(ExamineLog sysLog);
    ExamineLog findById(String id);
    List<ExamineLog> findAll();
}
