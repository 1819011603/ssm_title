package com.example.service.front.impl;


import com.example.dao.front.MemberQuestionDao;
import com.example.domain.front.MemberQuestion;

import com.example.service.front.MemberQuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/23  16:23
 * @VERSION 1.0
 */

@Service
public class MemberQuestionServiceImpl implements MemberQuestionService {
    @Resource
    private MemberQuestionDao memberQuestionDao;
    @Override
    public void save(MemberQuestion memberQuestion) {

        memberQuestionDao.save(memberQuestion);
    }

    @Override
    public void delete(String id) {
        memberQuestionDao.delete(id);
    }

    @Override
    public void update(MemberQuestion memberQuestion) {
        memberQuestionDao.update(memberQuestion);
    }

    @Override
    public MemberQuestion findById(String id) {
        return memberQuestionDao.findById(id);
    }

    @Override
    public List<MemberQuestion> findAll()

    {

        return memberQuestionDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size)
    {
        PageHelper.startPage(page,size);

        return new PageInfo(memberQuestionDao.findAll());
    }
}
