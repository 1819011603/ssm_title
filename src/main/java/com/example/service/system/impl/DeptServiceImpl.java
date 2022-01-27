package com.example.service.system.impl;

import com.example.dao.system.DeptDao;
import com.example.domain.store.Company;
import com.example.domain.system.Dept;
import com.example.service.system.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/15  21:34
 * @VERSION 1.0
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    DeptDao deptDao;

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
    public void save(Dept dept) {
        deptDao.save(dept);
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
        deptDao.delete(id);
    }

    @Override
    public void update(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> findAll() {
        return deptDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Dept> all = deptDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }

    @Override
    public Dept findByName(String parent) {
        return deptDao.findByName(parent);
    }
}
