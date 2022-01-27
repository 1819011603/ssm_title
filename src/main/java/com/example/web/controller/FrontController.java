package com.example.web.controller;

import com.example.domain.front.Member;
import com.example.domain.front.MemberQuestion;
import com.example.service.front.MemberQuestionService;
import com.example.service.front.MemberService;
import com.example.utils.BeanUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author 18190
 * @Date: 2021/7/23  15:59
 * @VERSION 1.0
 */

@Controller
@RequestMapping("/front")
public class FrontController {

    @Resource
    private MemberService memberService;
    @Resource
    private MemberQuestionService memberQuestionService;

    @RequestMapping("/memberQuestion")
    public ModelAndView frontMemberQuestion(HttpServletRequest request){
        String operation = request.getParameter("operation");
        ModelAndView mv = new ModelAndView();
        if("list".equals(operation)){
            Integer page;
            Integer size;

            String page1 = request.getParameter("page");
            if (page1 == null || "".equals(page1)){
                page = 1;
            }else {
                page = Integer.parseInt(page1);
            }
            String size1 = request.getParameter("size");
            if (size1 == null || "".equals(size1)){
                size = 5;
            }else {
                size = Integer.parseInt(size1);
            }
            PageInfo pageInfo = memberQuestionService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/front/memberQuestion/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("memberQuestionList",memberQuestionService.findAll());
            mv.setViewName("forward:/WEB-INF/pages/front/memberQuestion/add.jsp");
        }else if("save".equals(operation)){

            System.out.println("save");
            MemberQuestion memberQuestion = BeanUtil.fillBean(request,MemberQuestion.class,"yyyy-MM-dd");
            memberQuestion.setId(UUID.randomUUID().toString());
            memberQuestionService.save(memberQuestion);
            mv.setViewName("redirect:/front/memberQuestion?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            memberQuestionService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/front/memberQuestion?operation=list");
        }else if("toEdit".equals(operation)){
            mv.addObject("memberQuestionList",memberQuestionService.findAll());
            mv.addObject("memberQuestion",memberQuestionService.findById(request.getParameter("id")));
            mv.setViewName("forward:/WEB-INF/pages/front/memberQuestion/update.jsp");
        }else if("edit".equals(operation)){
            MemberQuestion memberQuestion = BeanUtil.fillBean(request, MemberQuestion.class,"yyyy-MM-dd");
            //调用业务层接口save
//        MemberQuestionService memberQuestionService = new MemberQuestionServiceImpl();
            memberQuestionService.update(memberQuestion);
            mv.setViewName("redirect:/front/memberQuestion?operation=list");
        }


        return mv;


    }

    @RequestMapping("/member")
    public ModelAndView frontMember(HttpServletRequest request){
        String operation = request.getParameter("operation");
        ModelAndView mv = new ModelAndView();
        if("list".equals(operation)){
            Integer page;
            Integer size;

            String page1 = request.getParameter("page");
            if (page1 == null || "".equals(page1)){
                page = 1;
            }else {
                page = Integer.parseInt(page1);
            }
            String size1 = request.getParameter("size");
            if (size1 == null || "".equals(size1)){
                size = 5;
            }else {
                size = Integer.parseInt(size1);
            }
            PageInfo pageInfo = memberService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/front/member/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("memberList",memberService.findAll());
            mv.setViewName("forward:/WEB-INF/pages/front/member/add.jsp");
        }else if("save".equals(operation)){

            System.out.println("save");
            Member member = BeanUtil.fillBean(request,Member.class,"yyyy-MM-dd");
            member.setId(UUID.randomUUID().toString());
            memberService.save(member);
            mv.setViewName("redirect:/front/member?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            memberService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/front/member?operation=list");
        }else if("toEdit".equals(operation)){
            mv.addObject("memberList",memberService.findAll());
            mv.addObject("member",memberService.findById(request.getParameter("id")));
            mv.setViewName("forward:/WEB-INF/pages/front/member/update.jsp");
        }else if("edit".equals(operation)){
            Member member = BeanUtil.fillBean(request, Member.class,"yyyy-MM-dd");
            //调用业务层接口save
//        MemberService memberService = new MemberServiceImpl();
            memberService.update(member);
            mv.setViewName("redirect:/front/member?operation=list");
        }


        return mv;


    }
}
