package com.example.dao.store;

import com.example.domain.store.Company;

import java.util.List;


/**
 * @author 18190
 * @Date: 2021/7/14  20:58
 * @VERSION 1.0
 */
public interface CompanyDao {
    int save(Company company);
    int delete(String id);
    int update(Company company);
    Company findById(String id);
    List<Company> findAll();

}
