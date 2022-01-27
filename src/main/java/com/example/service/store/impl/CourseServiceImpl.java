package com.example.service.store.impl;


import com.example.dao.store.CourseDao;
import com.example.domain.store.Course;
import com.example.service.store.CourseService;
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
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;
    public CourseServiceImpl(){
        System.out.println("CourseServiceImpl init");
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
    public void save(Course course) {
        course.setId(UUID.randomUUID().toString());
        course.setCreateTime(new Date(System.currentTimeMillis()));

        courseDao.save(course);
//        SqlSession sqlSession = null;
//        try {
//            sqlSession = MapperFactory.getSqlSession();
//            CourseDao courseDao = MapperFactory.getMapper(sqlSession,CourseDao.class);
//            courseDao.save(course);
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
        courseDao.delete(id);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public Course findById(String id) {
        return courseDao.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Course> all = courseDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }
}
