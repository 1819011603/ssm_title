package com.example.service.store.impl;


import com.example.dao.store.QuestionDao;
import com.example.domain.store.Question;

import com.example.service.store.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 18190
 * @Date: 2021/7/14  21:53
 * @VERSION 1.0
 */

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionDao questionDao;
    public QuestionServiceImpl(){
        System.out.println("QuestionServiceImpl init");
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
    public void save(Question question) {


        question.setCreateTime(new Date(System.currentTimeMillis()));
        question.setReviewStatus("0");

        questionDao.save(question);
//        SqlSession sqlSession = null;
//        try {
//            sqlSession = MapperFactory.getSqlSession();
//            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);
//            questionDao.save(question);
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

        questionDao.delete(id);
            File f1 = new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload\\" + id);
            System.out.println(f1.getPath());
            deleteDir(f1);
    }
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            boolean f = false;
            int j = 0;
            while (!f){
                String[] children = dir.list();
                f = true;
                try {
                    for (int i=0; i<children.length; i++) {
                        boolean success = deleteDir(new File(dir, children[i]));
                        if (!success) {
                            f = false;
                            break;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    f = false;
                }finally {

                    if(++j>5){
                        break;
                    }
                }
            }

        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    @Override
    public void update(Question question) {
        questionDao.update(question);
    }

    @Override
    public Question findById(String id) {
        return questionDao.findById(id);
    }

    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Question> all = questionDao.findAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }
}
