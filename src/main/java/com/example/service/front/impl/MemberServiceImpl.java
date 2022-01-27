package com.example.service.front.impl;

import com.example.dao.front.MemberDao;
import com.example.domain.front.Member;
import com.example.service.front.MemberService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/7/23  16:23
 * @VERSION 1.0
 */

@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberDao memberDao;
    @Override
    public void save(Member company) {
        company.setRegisterDate(new Date());
        company.setPassword(DigestUtils.md5DigestAsHex(company.getPassword().getBytes()));
        memberDao.save(company);
    }

    @Override
    public void delete(String id) {
        memberDao.delete(id);
    }

    @Override
    public void update(Member company) {
        memberDao.update(company);
    }

    @Override
    public Member findById(String id) {
        return memberDao.findById(id);
    }

    @Override
    public List<Member> findAll()

    {

        return memberDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size)
    {
        PageHelper.startPage(page,size);

        return new PageInfo(memberDao.findAll());
    }
}
