package com.example.web.controller;

import com.example.domain.RoleModule;
import com.example.domain.store.Company;
import com.example.domain.system.*;
import com.example.service.store.CompanyService;
import com.example.service.system.*;
import com.example.utils.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author 18190
 * @Date: 2021/7/15  11:47
 * @VERSION 1.0
 */
@Controller
public class SystemController {


    @Resource
    private DeptService deptService;


    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private ModuleService moduleService;

    @Resource
    private SysLogService sysLogService;

    public SystemController(){
        System.out.println("SystemController init");
    }

    @RequestMapping("/system/sysLog")
    public ModelAndView systemSysLog(HttpServletRequest request) throws ServletException, IOException, ParseException {

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
                size = 20;
            }else {
                size = Integer.parseInt(size1);
            }
            PageInfo pageInfo = sysLogService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/system/sysLog/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("sysLogList",sysLogService.findAll());

            mv.setViewName("forward:/WEB-INF/pages/system/sysLog/add.jsp");
        }else if("save".equals(operation)){

            System.out.println("save");
            SysLog sysLog = BeanUtil.fillBean(request,SysLog.class,"yyyy-MM-dd");


            sysLog.setId(UUID.randomUUID().toString());
            System.out.println(sysLog);
            sysLogService.save(sysLog);
            mv.setViewName("redirect:/system/sysLog?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            sysLogService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/system/sysLog?operation=list");
        }else if("toEdit".equals(operation)){
            System.out.println("toEdit");

            mv.addObject("sysLogList",sysLogService.findAll());
            mv.addObject("sysLog",sysLogService.findById(request.getParameter("id")));
            mv.setViewName("forward:/WEB-INF/pages/system/sysLog/update.jsp");
        }else if("edit".equals(operation)){
            System.out.println("edit");
            SysLog dept = BeanUtil.fillBean(request, SysLog.class,"yyyy-MM-dd");
            System.out.println(dept);
            //调用业务层接口save
//        DeptService deptService = new DeptServiceImpl();
            sysLogService.update(dept);
            mv.setViewName("redirect:/system/sysLog?operation=list");
        }else if("sysLogSysLogList".equals(operation)){
            System.out.println("sysLogSysLogList");
            mv.setViewName("forward:/WEB-INF/pages/system/sysLog/sysLog.jsp");
        }


        return mv;
    }


    @RequestMapping("/system/module")
    public ModelAndView systemModule(HttpServletRequest request) throws ServletException, IOException, ParseException {

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
            PageInfo pageInfo = moduleService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/system/module/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("moduleList",moduleService.findAll());

            mv.setViewName("forward:/WEB-INF/pages/system/module/add.jsp");
        }else if("save".equals(operation)){

            System.out.println("save");
            Module module = BeanUtil.fillBean(request,Module.class,"yyyy-MM-dd");
            int max=10000000,min=1000;
            int ran2 = (int) (Math.random()*(max-min)+min);
            module.setId(Integer.toString(ran2));
            System.out.println(module);
            moduleService.save(module);
            mv.setViewName("redirect:/system/module?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            moduleService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/system/module?operation=list");
        }else if("toEdit".equals(operation)){
            System.out.println("toEdit");

            mv.addObject("moduleList",moduleService.findAll());
            mv.addObject("module",moduleService.findById(request.getParameter("id")));
            mv.setViewName("forward:/WEB-INF/pages/system/module/update.jsp");
        }else if("edit".equals(operation)){
            System.out.println("edit");
            Module dept = BeanUtil.fillBean(request, Module.class,"yyyy-MM-dd");
            System.out.println(dept);
            //调用业务层接口save
//        DeptService deptService = new DeptServiceImpl();
            moduleService.update(dept);
            mv.setViewName("redirect:/system/module?operation=list");
        }else if("moduleModuleList".equals(operation)){
            System.out.println("moduleModuleList");
            mv.setViewName("forward:/WEB-INF/pages/system/module/module.jsp");
        }


        return mv;
    }


    @RequestMapping("/system/role")
    public ModelAndView systemRole(HttpServletRequest request) throws ServletException, IOException, ParseException {

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
            PageInfo pageInfo = roleService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/system/role/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("deptList",deptService.findAll());
            mv.setViewName("forward:/WEB-INF/pages/system/role/add.jsp");
        }else if("save".equals(operation)){

            System.out.println("save");
            Role role = BeanUtil.fillBean(request,Role.class,"yyyy-MM-dd");
            role.setId(UUID.randomUUID().toString());
            roleService.save(role);
            mv.setViewName("redirect:/system/role?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            roleService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/system/role?operation=list");
        }else if("toEdit".equals(operation)){
            System.out.println("toEdit");


            mv.addObject("role",roleService.findById(request.getParameter("id")));
            mv.setViewName("forward:/WEB-INF/pages/system/role/update.jsp");
        }else if("edit".equals(operation)){
            System.out.println("edit");
            Role dept = BeanUtil.fillBean(request, Role.class,"yyyy-MM-dd");
            System.out.println(dept);
            //调用业务层接口save
//        DeptService deptService = new DeptServiceImpl();
            roleService.update(dept);
            mv.setViewName("redirect:/system/role?operation=list");
        }else if("author".equals(operation)){
            System.out.println("author");
            String id = request.getParameter("id");
            Role byId = roleService.findById(id);
            mv.addObject("role",byId);

            List<RoleModule> modules = moduleService.findAllModuleByRoleId(id);
            modules.get(0).setpId(0);
            System.out.println(modules);
            ObjectMapper om = new ObjectMapper();
            String s = om.writeValueAsString(modules);

            System.out.println(s);
            mv.addObject("json",s);

            mv.setViewName("forward:/WEB-INF/pages/system/role/author.jsp");
        }else if("updateRoleModule".equals(operation)){
            String roleId = request.getParameter("roleId");
            System.out.println("updateRoleModule:" + roleId);
            String[] list = request.getParameter("moduleIds").split(",");
            System.out.println(Arrays.toString(list));
            roleService.deleteRoleAllModule(roleId);




                for (String s: list){
                    if(!"".equals(s)){

                        roleService.insertRoleModule(roleId,s);
                    }

                }






            mv.setViewName("redirect:/system/role?operation=list");


        }



        return mv;
    }
//    @RequestMapping("/system/role1")
//    @ResponseBody
//    public ModelAndView testAuthor(HttpServletRequest request) throws JsonProcessingException {
//        String operation = request.getParameter("operation");
//        if("author".equals(operation)){
//            System.out.println("author1");
//            String id = request.getParameter("id");
//            Role byId = roleService.findById(id);
//            request.setAttribute("role",byId);
//
//            List<RoleModule> modules = moduleService.findAllModuleByRoleId(id);
//            modules.get(0).setpId(0);
//            System.out.println(modules);
//            ObjectMapper om = new ObjectMapper();
//            String s = om.writeValueAsString(modules);
//            System.out.println(s);
//
//
//        }
//        return null;
//    }

    @RequestMapping("/system/user")
    public ModelAndView systemUser(HttpServletRequest request) throws ServletException, IOException, ParseException {

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
            PageInfo pageInfo = userService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/system/user/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("deptList",deptService.findAll());
            mv.setViewName("forward:/WEB-INF/pages/system/user/add.jsp");
        }else if("save".equals(operation)){

            System.out.println("save");
            User user = BeanUtil.fillBean(request,User.class,"yyyy-MM-dd");
            user.setId(UUID.randomUUID().toString());
            userService.save(user);
            mv.setViewName("redirect:/system/user?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            System.out.println("delete:" + id);
            userService.delete(userService.findById(id));
            mv.setViewName("redirect:/system/user?operation=list");
        }else if("toEdit".equals(operation)){
            System.out.println("toEdit");

            mv.addObject("deptList",deptService.findAll());
            mv.addObject("user",userService.findById(request.getParameter("id")));
            mv.setViewName("forward:/WEB-INF/pages/system/user/update.jsp");
        }else if("edit".equals(operation)){
            System.out.println("edit");
            User dept = BeanUtil.fillBean(request, User.class,"yyyy-MM-dd");
            System.out.println(dept);
            //调用业务层接口save
//        DeptService deptService = new DeptServiceImpl();
            userService.update(dept);
            mv.setViewName("redirect:/system/user?operation=list");
        }else if("userRoleList".equals(operation)){
            String id = request.getParameter("id");
            System.out.println("userRoleList:" + id);
            User byId = userService.findById(id);
            mv.addObject("user",byId);

            List<Role> roleList = roleService.findRoleById(id);
            System.out.println(roleList);
            mv.addObject("roleList",roleList);


            System.out.println(byId);
            mv.setViewName("forward:/WEB-INF/pages/system/user/role.jsp");
        }else if("updateRole".equals(operation)){
            String id = request.getParameter("id");
//            String[] list = request.getParameter("list").split(",");
            String[] list = request.getParameterValues("roleId");
            System.out.println(Arrays.toString(list));
            userService.deleteUserAllRole(id);
            System.out.println("updateRole");


                if (list!=null){
                    for (String s: list){

                        userService.insertUserRole(id,s);

                    }
                }





            mv.setViewName("redirect:/system/user?operation=list");

        }else if("login".equals(operation)){
            String email = request.getParameter("email");

            String password = request.getParameter("password");

            User login = userService.login(email, password);
            if(login != null){
                request.getSession().setAttribute("login",login);


//                List<Role> roleById = roleService.findRoleById(login.getId());
//                System.out.println(roleById);
//                Set<Module> set = new HashSet<>();
//                for (Role r: roleById){
//                    if("checked".equals(r.getRemark())){
//                        List<Module> allModuleByRoleId1 = moduleService.findAllModuleByRoleId1(r.getId());
//                        for (Module m: allModuleByRoleId1){
//                            if("1".equals(m.getCtype())){
//                                set.add(m);
//                            }
//                        }
//                    }
//
//
//                }
//                List<Module> list = new ArrayList<>(set);


//                System.out.println(list.size());

                List<Module> list = moduleService.findAllModuleByUserId(login.getId());
                request.getSession().setAttribute("modules",list);
                StringBuilder stringBuilder = new StringBuilder();
                if(list!= null){
                    for (Module m:list){
                        stringBuilder.append(m.getCurl()).append(',');
                    }
                }

                request.getSession().setAttribute("authorList",stringBuilder.toString());

                mv.setViewName("forward:/WEB-INF/pages/home/main.jsp");
            }else {
                mv.setViewName("redirect:/login.jsp");
            }
        }
        else if("main".equals(operation)){
            System.out.println("main");
                mv.setViewName("forward:/WEB-INF/pages/home/home.jsp");

        }
        else if("logout".equals(operation)){
            System.out.println("logout");
            request.removeAttribute("login");
            mv.setViewName("redirect:/login.jsp");

        }


        return mv;
    }

    @RequestMapping("/system/dept")
    public ModelAndView systemDept(HttpServletRequest request) throws ServletException, IOException, ParseException {

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
            PageInfo pageInfo = deptService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/system/dept/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("deptList",deptService.findAll());
            mv.setViewName("forward:/WEB-INF/pages/system/dept/add.jsp");
        }else if("save".equals(operation)){

            System.out.println("save");
            Dept dept = BeanUtil.fillBean(request,Dept.class,"yyyy-MM-dd");
            dept.setId(UUID.randomUUID().toString());
            deptService.save(dept);
            mv.setViewName("redirect:/system/dept?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            deptService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/system/dept?operation=list");
        }else if("toEdit".equals(operation)){
            mv.addObject("deptList",deptService.findAll());
            mv.addObject("dept",deptService.findById(request.getParameter("id")));
            mv.setViewName("forward:/WEB-INF/pages/system/dept/update.jsp");
        }else if("edit".equals(operation)){
            Dept dept = BeanUtil.fillBean(request,Dept.class,"yyyy-MM-dd");
            //调用业务层接口save
//        DeptService deptService = new DeptServiceImpl();
            deptService.update(dept);
            mv.setViewName("redirect:/system/dept?operation=list");
        }


        return mv;
    }



}
