package com.example.service.store;


import com.example.domain.store.QuestionItem;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/14  21:50
 * @VERSION 1.0
 */
public interface QuestionItemService {

    void save(QuestionItem company);
    void delete(String id);
    void update(QuestionItem company);
    QuestionItem findById(String id);
    List<QuestionItem> findAll(String questionId);
    PageInfo findAll(int page, int size,String questionId);
    void deleteAll(String questionId);
}
