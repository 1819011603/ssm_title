package com.example.service.store;

import com.example.domain.store.ExamineLog;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/22  18:38
 * @VERSION 1.0
 */
public interface ExamineLogService {
    void save(ExamineLog sysLog, HttpSession session);
    void delete(String id);
    void update(ExamineLog sysLog, HttpSession session);
    ExamineLog findById(String id);
    List<ExamineLog> findAll();
    PageInfo findAll(int page, int size);
}
