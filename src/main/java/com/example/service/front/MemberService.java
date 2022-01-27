package com.example.service.front;

import com.example.domain.front.Member;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/23  16:22
 * @VERSION 1.0
 */
public interface MemberService {
    void save(Member company);
    void delete(String id);
    void update(Member company);
    Member findById(String id);
    List<Member> findAll();
    PageInfo findAll(int page, int size);
}
