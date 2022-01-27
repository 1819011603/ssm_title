package com.example.service.system;


import com.example.domain.system.Role;
import com.example.domain.system.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    User login(String email,String password);

    /**
     * 添加
     * @param user
     * @return
     */
    void save(User user);

    /**
     * 删除
     * @param user
     * @return
     */
    void delete(User user);
    void deleteUserAllRole(String userId);
    void insertUserRole( String userId, String roleId);
    /**
     * 修改
     * @param user
     * @return
     */
    void update(User user);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    User findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<User> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);
}
