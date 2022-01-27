package com.example.service.store.impl;

import com.example.dao.store.CompanyDao;
import com.example.domain.store.Company;
import com.example.factory.MapperFactory;
import com.example.service.store.CompanyService;
import com.example.utils.TransactionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author 18190
 * @Date: 2021/7/14  21:53
 * @VERSION 1.0
 */

@Service
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyDao companyDao;
    public CompanyServiceImpl(){
        System.out.println("CompanyServiceImpl init");
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
    public void save(Company company) {
        company.setId(UUID.randomUUID().toString());

        companyDao.save(company);
//        SqlSession sqlSession = null;
//        try {
//            sqlSession = MapperFactory.getSqlSession();
//            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);
//            companyDao.save(company);
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
        companyDao.delete(id);
    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }

    @Override
    public Company findById(String id) {
        return companyDao.findById(id);
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Company> all = companyDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }
}
