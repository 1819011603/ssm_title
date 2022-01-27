package com.example.service.store;


import com.example.domain.store.Catalog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/14  21:50
 * @VERSION 1.0
 */
public interface CatalogService {

    void save(Catalog company);
    void delete(String id);
    void update(Catalog company);
    Catalog findById(String id);
    List<Catalog> findAll();
    PageInfo findAll(int page, int size);
}
