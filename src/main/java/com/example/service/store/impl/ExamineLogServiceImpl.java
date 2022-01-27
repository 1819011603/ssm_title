package com.example.service.store.impl;

import com.example.dao.store.ExamineLogDao;
import com.example.domain.store.ExamineLog;
import com.example.domain.system.User;
import com.example.service.store.ExamineLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 18190
 * @Date: 2021/7/22  18:55
 * @VERSION 1.0
 *
 *
 */

@Service
public class ExamineLogServiceImpl implements ExamineLogService {
    @Resource
    private ExamineLogDao examineLogDao;
    @Override
    public void save(ExamineLog sysLog, HttpSession session) {
        sysLog.setCreateTime(new Date());
        User user = (User) session.getAttribute("login");
        sysLog.setUserId(user.getId());
        sysLog.setId(UUID.randomUUID().toString());
        sysLog.setCreateDept(user.getDept().getDeptName());
        sysLog.setCreateBy(user.getUserName());
        System.out.println(sysLog);
        examineLogDao.save(sysLog);
    }

    @Override
    public void delete(String id) {
        examineLogDao.delete(id);
    }

    @Override
    public void update(ExamineLog sysLog, HttpSession session) {
        sysLog.setUpdateTime(new Date());
        User user = (User) session.getAttribute("login");
        sysLog.setUpdateBy(user.getUserName());
        examineLogDao.update(sysLog);
    }

    @Override
    public ExamineLog findById(String id) {
        return examineLogDao.findById(id);
    }

    @Override
    public List<ExamineLog> findAll() {
        return examineLogDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<ExamineLog> all = examineLogDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }
}
