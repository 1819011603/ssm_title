package com.example.dao.system;


import com.example.domain.RoleModule;
import com.example.domain.system.Module;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/16  19:03
 * @VERSION 1.0
 */
public interface ModuleDao {
    int save(Module course);
    int delete(String id);
    int update(Module course);
    Module findById(String id);
    List<Module> findAll();
    List<RoleModule> findAllModuleByRoleId(String roleId);
    List<Module> findAllModuleByRoleId1(String roleId);
    List<Module> findAllModuleByUserId(String UserId);

    int deleteRoleModuleTableById(String id);

    int adminAutoAddPower(String module_id);
}
