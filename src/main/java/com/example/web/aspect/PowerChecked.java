package com.example.web.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 18190
 * @Date: 2021/7/20  20:25
 * @VERSION 1.0
 */



@Aspect
public class PowerChecked {
    public PowerChecked(){
        System.out.println("PowerChecked init");
    }
    @Before(value = "execution(* com.example.service..*.*(..))")
    public void checkedPower(){

        System.out.println("checked Power!");
    }

}
