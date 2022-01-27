package com.example.service;

import com.example.domain.store.Company;
import com.example.service.store.CompanyService;
import com.example.service.store.impl.CompanyServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author 18190
 * @Date: 2021/7/14  22:02
 * @VERSION 1.0
 */
public class CompanyServiceTest {

    private static CompanyService companyService = null;
    @BeforeClass
    public static void init(){
        companyService = new CompanyServiceImpl();
    }

    @Test
    public void testSave(){
        Company company = new Company();
        company.setName("第一次测试Test");
        companyService.save(company);

    }
    @AfterClass
    public static void destory(){
        companyService = null;
    }
}
