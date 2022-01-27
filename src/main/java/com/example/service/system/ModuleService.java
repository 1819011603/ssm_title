package com.example.service.system;


import com.example.domain.RoleModule;
import com.example.domain.system.Module;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ModuleService {
    /**
     * 添加
     * @param user
     * @return
     */
    void save(Module user);

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
    void update(Module user);

    /**
     * 查询单个
     * @param id 查询的条件（id）
     * @return 查询的结果，单个对象
     */
    Module findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Module> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示的数据总量
     * @return
     */
    PageInfo findAll(int page, int size);

    List<RoleModule> findAllModuleByRoleId(String roleId);

    List<Module> findAllModuleByRoleId1(String id);
    List<Module> findAllModuleByUserId(String id);
}
