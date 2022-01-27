package com.example.dao.store;


import com.example.domain.store.Catalog;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/16  19:03
 * @VERSION 1.0
 */
public interface CatalogDao {
    int save(Catalog course);
    int delete(String id);
    int update(Catalog course);
    Catalog findById(String id);
    List<Catalog> findAll();
}
