package com.example.service.front;


import com.example.domain.front.MemberQuestion;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/23  16:07
 * @VERSION 1.0
 */
public interface MemberQuestionService {
    void save(MemberQuestion memberQuestion);
    void delete(String id);
    void update(MemberQuestion memberQuestion);
    MemberQuestion findById(String id);
    List<MemberQuestion> findAll();
     PageInfo findAll(int page, int size);

}
