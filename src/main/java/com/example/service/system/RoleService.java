package com.example.service.system;


import com.example.domain.system.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    /**
     * 添加
     * @param user
     * @return
     */
    void save(Role user);
    List<Role> findRoleById(String userId);
    /**
     * 删除

     * @return
     */
    void delete(String id);

    /**
     * 修改
     * @param user
     * @return
     */
    void update(Role user);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    Role findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Role> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);

    void deleteRoleAllModule(String roleId);
    void insertRoleModule(String roleId,String moduleId);
}
