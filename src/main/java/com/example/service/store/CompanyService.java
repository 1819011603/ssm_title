package com.example.service.store;

import com.example.domain.store.Company;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/14  21:50
 * @VERSION 1.0
 */
public interface CompanyService {

    void save(Company company);
    void delete(String id);
    void update(Company company);
    Company findById(String id);
    List<Company> findAll();
    PageInfo findAll(int page,int size);
}
