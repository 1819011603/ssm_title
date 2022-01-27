package com.example.service.system.impl;

import com.example.dao.system.RoleDao;
import com.example.domain.system.Role;
import com.example.service.system.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

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
    public void save(Role role) {
        role.setCreateTime(new Date());
        roleDao.save(role);
    }

    @Override
    public List<Role> findRoleById(String userId) {
        return roleDao.findRoleById(userId);
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
        roleDao.deleteRoleAllModule(id);

        roleDao.delete(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Role> all = roleDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }

    @Override
    public void deleteRoleAllModule(String roleId) {
        roleDao.deleteRoleAllModule(roleId);
    }

    @Override
    public void insertRoleModule(String roleId, String moduleId) {
        roleDao.insertRoleModule(roleId,moduleId);
    }
}
