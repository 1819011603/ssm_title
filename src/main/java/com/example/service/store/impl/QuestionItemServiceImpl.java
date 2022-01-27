package com.example.service.store.impl;

import com.example.dao.store.QuestionItemDao;
import com.example.domain.store.QuestionItem;

import com.example.service.store.QuestionItemService;
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
 * @Date: 2021/7/18  12:10
 * @VERSION 1.0
 */
@Service
public class QuestionItemServiceImpl implements QuestionItemService {
    @Resource
    private QuestionItemDao questionItemDao;
    public QuestionItemServiceImpl(){
        System.out.println("QuestionItemServiceImpl init");
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
    public void save(QuestionItem questionItem) {



        questionItemDao.save(questionItem);
//        SqlSession sqlSession = null;
//        try {
//            sqlSession = MapperFactory.getSqlSession();
//            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);
//            questionItemDao.save(questionItem);
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
        QuestionItem byId = questionItemDao.findById(id);
        questionItemDao.delete(id);
        File f1 = new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload\\" + byId.getQuestionId()+"\\"+byId.getId());
        System.out.println(f1.getPath());
        if (f1.exists() && f1.isDirectory()){
            for (File f: f1.listFiles()){
                f.delete();
            }
            f1.delete();
        }

    }


    @Override
    public void update(QuestionItem questionItem) {
        questionItemDao.update(questionItem);
    }

    @Override
    public QuestionItem findById(String id) {
        return questionItemDao.findById(id);
    }

    @Override
    public List<QuestionItem> findAll(String questionId) {
        return questionItemDao.findAll(questionId);
    }

    @Override
    public PageInfo findAll(int page, int size,String questionId) {
        PageHelper.startPage(page,size);
        List<QuestionItem> all = questionItemDao.findAll(questionId);
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }

    @Override
    public void deleteAll(String questionId) {
        questionItemDao.deleteAll(questionId);
    }
}
