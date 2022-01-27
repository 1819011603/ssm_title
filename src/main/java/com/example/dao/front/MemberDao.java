package com.example.dao.front;


import com.example.domain.front.Member;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/23  16:07
 * @VERSION 1.0
 */
public interface MemberDao {
    int save(Member course);
    int delete(String id);
    int update(Member course);
    Member findById(String id);
    List<Member> findAll();

}
