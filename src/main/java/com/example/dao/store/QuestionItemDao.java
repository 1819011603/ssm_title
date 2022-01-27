package com.example.dao.store;


import com.example.domain.store.QuestionItem;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/16  19:03
 * @VERSION 1.0
 */
public interface QuestionItemDao {
    int save(QuestionItem course);
    int delete(String id);
    int update(QuestionItem course);
    QuestionItem findById(String id);
    List<QuestionItem> findAll(String questionId);
    int deleteAll(String questionId);
}
