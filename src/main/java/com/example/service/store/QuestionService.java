package com.example.service.store;


import com.example.domain.store.Question;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/14  21:50
 * @VERSION 1.0
 */
public interface QuestionService {

    void save(Question company);
    void delete(String id);
    void update(Question company);
    Question findById(String id);
    List<Question> findAll();
    PageInfo findAll(int page, int size);
}
