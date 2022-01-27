package com.example.dao.system;


import com.example.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int save(User user);

    int delete(User user);

    int update(User user);
    int deleteUserAllRole(String userId);
    int insertUserRole(@Param("userId") String userId, @Param("roleId") String roleId);
    User findById(String id);

    List<User> findAll();
    User login(@Param("email")String email,@Param("password")String password);

}
