package com.example.dao.front;


import com.example.domain.front.Member;
import com.example.domain.front.MemberQuestion;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/23  16:07
 * @VERSION 1.0
 */
public interface MemberQuestionDao {
    int save(MemberQuestion memberQuestion);
    int delete(String id);
    int update(MemberQuestion memberQuestion);
    MemberQuestion findById(String id);
    List<MemberQuestion> findAll();

}
