package com.example.dao.store;


import com.example.domain.store.Question;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/16  19:03
 * @VERSION 1.0
 */
public interface QuestionDao {
    int save(Question course);
    int delete(String id);
    int update(Question course);
    Question findById(String id);
    List<Question> findAll();
}
