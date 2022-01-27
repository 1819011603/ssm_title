package com.example.service.system.impl;


import com.example.dao.system.ModuleDao;
import com.example.domain.RoleModule;
import com.example.domain.system.Module;
import com.example.service.system.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/15  23:46
 * @VERSION 1.0
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleDao moduleDao;

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
    public void save(Module module) {
        moduleDao.adminAutoAddPower(module.getId());
        moduleDao.save(module);
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
        moduleDao.deleteRoleModuleTableById(id);
        moduleDao.delete(id);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public Module findById(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Module> all = moduleDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }

    @Override
    public List<RoleModule> findAllModuleByRoleId(String roleId) {
        return moduleDao.findAllModuleByRoleId(roleId);
    }

    @Override
    public List<Module> findAllModuleByRoleId1(String id) {
        return moduleDao.findAllModuleByRoleId1(id);
    }

    @Override
    public List<Module> findAllModuleByUserId(String id) {
        return  moduleDao.findAllModuleByUserId(id);
    }
}
