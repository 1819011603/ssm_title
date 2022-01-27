package com.example.service.system.impl;

import com.example.dao.system.RoleDao;
import com.example.dao.system.UserDao;
import com.example.domain.system.Dept;
import com.example.domain.system.Role;
import com.example.domain.system.User;
import com.example.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/15  23:46
 * @VERSION 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;



    @Override
    public User login(String email, String password) {
        return userDao.login(email,DigestUtils.md5DigestAsHex(password.getBytes()));
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
    public void save(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userDao.save(user);
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
    public void delete(User user) {
        userDao.deleteUserAllRole(user.getId());
        userDao.delete(user);
    }

    @Override
    public void deleteUserAllRole(String userId) {
        userDao.deleteUserAllRole(userId);
    }

    @Override
    public void insertUserRole(String userId, String roleId) {
        userDao.insertUserRole(userId,roleId);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<User> all = userDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }
}
