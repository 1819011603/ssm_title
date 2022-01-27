package com.example.service.system.impl;

import com.example.dao.system.SysLogDao;
import com.example.domain.system.Dept;
import com.example.domain.system.SysLog;
import com.example.service.system.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/21  20:48
 * @VERSION 1.0
 */

@Service
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogDao sysLogDao;
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            // 发生这些异常会回滚
            rollbackFor = {
                    NullPointerException.class
            }
    )
    @Override
    public void save(SysLog dept) {
        sysLogDao.save(dept);
    }
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            // 发生这些异常会回滚
            rollbackFor = {
                    NullPointerException.class
            }
    )
    @Override
    public void delete(String id) {
        sysLogDao.delete(id);
    }

    @Override
    public void update(SysLog dept) {
        sysLogDao.update(dept);
    }

    @Override
    public SysLog findById(String id) {
        return sysLogDao.findById(id);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<SysLog> all = sysLogDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }
}
