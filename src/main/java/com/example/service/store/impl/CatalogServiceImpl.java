package com.example.service.store.impl;


import com.example.dao.store.CatalogDao;
import com.example.domain.store.Catalog;

import com.example.service.store.CatalogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 18190
 * @Date: 2021/7/14  21:53
 * @VERSION 1.0
 */

@Service
public class CatalogServiceImpl implements CatalogService {
    @Resource
    private CatalogDao catalogDao;
    public CatalogServiceImpl(){
        System.out.println("CatalogServiceImpl init");
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            // 发生这些异常会回滚
            rollbackFor = {
                    NullPointerException.class
            }
    )
    @Override
    public void save(Catalog catalog) {

        catalog.setId(UUID.randomUUID().toString());
        catalog.setCreateTime(new Date(System.currentTimeMillis()));
        catalogDao.save(catalog);
//        SqlSession sqlSession = null;
//        try {
//            sqlSession = MapperFactory.getSqlSession();
//            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession,CatalogDao.class);
//            catalogDao.save(catalog);
////            TransactionUtil.commit(sqlSession);
//        }catch (Exception e){
//            TransactionUtil.rollback(sqlSession);
//            throw new RuntimeException(e);
//        }finally {
//            try {
//                TransactionUtil.close(sqlSession);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }

        @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            // 发生这些异常会回滚
            rollbackFor = {
                    NullPointerException.class
            }
    )
    @Override
    public void delete(String id) {
        catalogDao.delete(id);
    }

    @Override
    public void update(Catalog catalog) {
        catalogDao.update(catalog);
    }

    @Override
    public Catalog findById(String id) {
        return catalogDao.findById(id);
    }

    @Override
    public List<Catalog> findAll() {
        return catalogDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Catalog> all = catalogDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }
}
