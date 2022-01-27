package com.example.dao.system;



import com.example.domain.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/16  19:03
 * @VERSION 1.0
 */
public interface RoleDao {
    int save(Role course);
    int delete(String id);
    int update(Role course);
    Role findById(String id);
    List<Role> findAll();
    List<Role> findRoleById( String userId);
    int deleteRoleAllModule(String roleId);

    int insertRoleModule(@Param("roleId")String roleId, @Param("moduleId")String moduleId);
}
