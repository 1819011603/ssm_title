package com.example.web.controller;

import com.example.domain.store.*;
import com.example.service.store.*;
import com.example.utils.BeanUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author 18190
 * @Date: 2021/7/15  11:47
 * @VERSION 1.0
 */
@Controller
public class StoreController {
    @Resource
    private CompanyService service;

    @Resource
    private CourseService courseService;


    @Resource
    private CatalogService catalogService;


    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionItemService questionItemService;

    @Resource
    private ExamineLogService examineLogService;

    @RequestMapping("/store/questionItem")
    public ModelAndView storeQuestionItem(HttpServletRequest request) throws Exception {
        String operation = request.getParameter("operation");
        ModelAndView mv = new ModelAndView();
        if("list".equals(operation)){
            questionItemList(request,mv,request.getParameter("id"));
            request.setAttribute("operation","save");
            mv.setViewName("forward:/WEB-INF/pages/store/questionItem/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");

            mv.setViewName("redirect:/store/questionItem?operation=list&id="+  request.getParameter("id"));
        }else if("save".equals(operation)){
            System.out.println("save");
            QuestionItem questionItem = null;
            if(ServletFileUpload.isMultipartContent(request)){
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                 questionItem = BeanUtil.fillBean(fileItems,QuestionItem.class);
                System.out.println(questionItem);
                questionItem.setId(UUID.randomUUID().toString());
                File file;

                for (FileItem f :
                        fileItems) {
                    System.out.println(f);
                    if(!f.isFormField() && f.getSize() > 0){
                        file = new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload\\" + questionItem.getQuestionId()+"\\" + questionItem.getId());
                        if((!file.exists()) || (!file.isDirectory())){
                            file.mkdirs();
                        }
                        f.write(new File(file,f.getName()));
                        questionItem.setPicture(questionItem.getQuestionId()+"/" + questionItem.getId()+"/" + f.getName());
                    }

                }
                questionItemService.save(questionItem);


            }

            mv.setViewName("redirect:/store/questionItem?operation=list&id="+questionItem.getQuestionId());
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            questionItemService.delete(id);
            System.out.println("delete:" + id);
            System.out.println(request.getParameter("questionId"));
            mv.setViewName("redirect:/store/questionItem?operation=list&id="+request.getParameter("questionId"));
        }else if("toEdit".equals(operation)){
            String id = request.getParameter("id");
            QuestionItem questionItem = questionItemService.findById(id);

            System.out.println("toEdit:"+ id);
            System.out.println(questionItem);
            mv.addObject("questionItem",questionItem);
            mv.addObject("operation","edit");

            questionItemList(request,mv,questionItem.getQuestionId());
            mv.setViewName("forward:/WEB-INF/pages/store/questionItem/list.jsp?id="+questionItem.getQuestionId());
        }else if("edit".equals(operation)){
            String id = request.getParameter("itemId");
            System.out.println("edit:"+ id);
            QuestionItem questionItem1 = null;

            if(ServletFileUpload.isMultipartContent(request)){
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                questionItem1 = BeanUtil.fillBean(fileItems,QuestionItem.class);
                System.out.println(questionItem1);

                File file;
                for (FileItem f :
                        fileItems) {
                    System.out.println(f);
                    if(!f.isFormField() && f.getSize() > 0){
                        file = new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload\\" + questionItem1.getQuestionId()+"\\" + questionItem1.getId());
                        if((!file.exists()) || (!file.isDirectory())){
                            file.mkdirs();
                        }else {
                            File f1 = new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload\\" +  questionItem1.getPicture());
                            System.out.println(f1.getPath());
                            if (f1.exists() && f1.isFile()){
                                f1.delete();
                            }

                        }
                        f.write(new File(file,f.getName()));
                        questionItem1.setPicture( questionItem1.getQuestionId()+"/" + questionItem1.getId()+"/" + f.getName());
                    }

                }
                questionItemService.update(questionItem1);


            }

            mv.setViewName("redirect:/store/questionItem?operation=list&id=" + questionItem1.getQuestionId());
        }else if("toExport".equals(operation)){

        }else if("toUpload".equals(operation)){
            System.out.println("toUpload");
            mv.setViewName("forward:/WEB-INF/pages/store/questionItem/testFileUpload.jsp");
        }else if("testFileUpload".equals(operation)){
            System.out.println("testFileUpload");
            if(ServletFileUpload.isMultipartContent(request)){
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);

                for (FileItem f :
                        fileItems) {
                    if(!f.isFormField()){
                        f.write(new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload",f.getName()) );
                    }

                }

            }


        }


        return mv;
    }


    public ByteArrayOutputStream exportXlsx(List<Question> questionList) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet s = wb.createSheet("title");

        s.addMergedRegion(new CellRangeAddress(1,1,1,12));
        Row row = s.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("在线试题导出信息");

        CellStyle cs_field = wb.createCellStyle();
        cs_field.setAlignment(HorizontalAlignment.CENTER);
        cs_field.setBorderTop(BorderStyle.THIN);
        cs_field.setBorderBottom(BorderStyle.THIN);
        cs_field.setBorderLeft(BorderStyle.THIN);
        cs_field.setBorderRight(BorderStyle.THIN);

        cell.setCellStyle(cs_field);
        String[] fields = {"题目ID","所属公司ID","所属目录ID","题目简介","题干描述",
                "题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
        Row row_2 = s.createRow(2);

        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_temp = row_2.createCell(1 + i); //++
            cell_2_temp.setCellValue(fields[i]);    //++
            cell_2_temp.setCellStyle(cs_field);
        }



        int row_index = 0;
        for (Question q : questionList) {
            int cell_index = 0;
            Row row_temp = s.createRow(3 + row_index++);

            Cell cell_data_1 = row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());    //++
            cell_data_1.setCellStyle(cs_field);

            Cell cell_data_2 = row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getCompanyId());    //++
            cell_data_2.setCellStyle(cs_field);

            Cell cell_data_3 = row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getCatalogId());    //++
            cell_data_3.setCellStyle(cs_field);

            Cell cell_data_4 = row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getRemark());    //++
            cell_data_4.setCellStyle(cs_field);

            Cell cell_data_5 = row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getSubject());    //++
            cell_data_5.setCellStyle(cs_field);

            Cell cell_data_6 = row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getPicture());    //++
            cell_data_6.setCellStyle(cs_field);

            Cell cell_data_7 = row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getAnalysis());    //++
            cell_data_7.setCellStyle(cs_field);

            Cell cell_data_8 = row_temp.createCell(1 + cell_index++);
            cell_data_8.setCellValue(q.getType());    //++
            cell_data_8.setCellStyle(cs_field);

            Cell cell_data_9 = row_temp.createCell(1 + cell_index++);
            cell_data_9.setCellValue(q.getDifficulty());    //++
            cell_data_9.setCellStyle(cs_field);

            Cell cell_data_10 = row_temp.createCell(1 + cell_index++);
            cell_data_10.setCellValue(q.getIsClassic());    //++
            cell_data_10.setCellStyle(cs_field);

            Cell cell_data_11 = row_temp.createCell(1 + cell_index++);
            cell_data_11.setCellValue(q.getState());    //++
            cell_data_11.setCellStyle(cs_field);

            Cell cell_data_12 = row_temp.createCell(1 + cell_index++);
            cell_data_12.setCellValue(q.getReviewStatus());    //++
            cell_data_12.setCellStyle(cs_field);
        }


        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        wb.write(bo);
        wb.close();
        return bo;
    }

    public void questionItemList(HttpServletRequest request,ModelAndView mv,String id){
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
        PageInfo pageInfo = questionItemService.findAll(page,size, id);
        request.setAttribute("page",pageInfo);
        request.setAttribute("questionId",id);
        System.out.println("list");


    }

    @RequestMapping("/store/examineLog")
    public ModelAndView storeExamineLog(HttpServletRequest request) throws ServletException, IOException, ParseException {
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
            PageInfo pageInfo = examineLogService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/store/examineLog/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.setViewName("forward:/WEB-INF/pages/store/examineLog/add.jsp");
        }else if("save".equals(operation)){
            System.out.println("save");
            ExamineLog examineLog = BeanUtil.fillBean(request, ExamineLog.class,"yyyy-MM-dd");

            examineLogService.save(examineLog,request.getSession());
            mv.setViewName("redirect:/store/examineLog?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            examineLogService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/store/examineLog?operation=list");
        }else if("toEdit".equals(operation)){
            String id = request.getParameter("id");
            ExamineLog examineLog = examineLogService.findById(id);

            System.out.println("toEdit:"+ id);
            System.out.println(examineLog);
            mv.addObject("examineLog",examineLog);
            mv.setViewName("forward:/WEB-INF/pages/store/examineLog/update.jsp?id="+id);
        }else if("edit".equals(operation)){
            String id = request.getParameter("id");
            System.out.println("edit:"+ id);


            ExamineLog examineLog = BeanUtil.fillBean(request, ExamineLog.class,"yyyy-MM-dd");
            System.out.println(examineLog);
            examineLogService.update(examineLog,request.getSession());

            mv.setViewName("redirect:/store/examineLog?operation=list");
        }


        return mv;
    }

    public StoreController(){
        System.out.println("StoreController init");
    }
    @RequestMapping("/store/question")
    public ModelAndView storeQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            PageInfo pageInfo = questionService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/store/question/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("companyList",service.findAll());
            mv.addObject("catalogList",catalogService.findAll());
            mv.setViewName("forward:/WEB-INF/pages/store/question/add.jsp");
        }else if("save".equals(operation)){
            System.out.println("save");

            if(ServletFileUpload.isMultipartContent(request)){
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                Question question = BeanUtil.fillBean(fileItems,Question.class);
                System.out.println(question);
                question.setId(UUID.randomUUID().toString());
                File file;

                for (FileItem f :
                        fileItems) {
                    System.out.println(f);
                    if(!f.isFormField() && f.getSize() > 0){
                        file = new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload\\" + question.getId());
                        if((!file.exists()) || (!file.isDirectory())){
                            file.mkdirs();
                        }
                        f.write(new File(file,f.getName()));
                        question.setPicture(question.getId() + "/" + f.getName());
                    }

                }
                questionService.save(question);


            }

            mv.setViewName("redirect:/store/question?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            questionService.delete(id);
            System.out.println("delete:" + id);
            questionItemService.deleteAll(id);
            mv.setViewName("redirect:/store/question?operation=list");
        }else if("toEdit".equals(operation)){
            String id = request.getParameter("id");
            Question question = questionService.findById(id);

            System.out.println("toEdit:"+ id);
            System.out.println(question);
            mv.addObject("companyList",service.findAll());
            mv.addObject("catalogList",catalogService.findAll());
            mv.addObject("question",question);
            mv.setViewName("forward:/WEB-INF/pages/store/question/update.jsp?id="+id);
        }else if("edit".equals(operation)){
            String id = request.getParameter("id");
            System.out.println("edit:"+ id);


            if(ServletFileUpload.isMultipartContent(request)){
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                Question question = BeanUtil.fillBean(fileItems,Question.class);
                System.out.println(question);
                Question question1 = questionService.findById(question.getId());
                File file;
                for (FileItem f :
                        fileItems) {
                    System.out.println(f);
                    if(!f.isFormField() && f.getSize() > 0){
                        file = new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload\\" + question.getId());
                        if((!file.exists()) || (!file.isDirectory())){
                            file.mkdirs();
                        }else {
                            File f1 = new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload\\" + question1.getPicture());
                            System.out.println(f1.getPath());
                            if (f1.exists() && f1.isFile()){


                                f1.delete();
                            }

                        }
                        f.write(new File(file,f.getName()));
                        question.setPicture(question.getId() + "/" + f.getName());
                    }

                }
                questionService.update(question);


            }


            mv.setViewName("redirect:/store/question?operation=list");
        }else if("toExport".equals(operation)){
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            String filename = new String("测试文件.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            response.setHeader("Content-Disposition","attachment;filename="+filename);
            ByteArrayOutputStream byteArrayOutputStream = exportXlsx( questionService.findAll());
            ServletOutputStream outputStream = response.getOutputStream();
            byteArrayOutputStream.writeTo(outputStream);
            System.out.println("toExport");
            outputStream.flush();
            outputStream.close();
//            mv.setViewName("redirect:/store/question?operation=list");
        }else if("toUpload".equals(operation)){
            System.out.println("toUpload");
            mv.setViewName("forward:/WEB-INF/pages/store/question/testFileUpload.jsp");
        }else if("testFileUpload".equals(operation)){
            System.out.println("testFileUpload");
            if(ServletFileUpload.isMultipartContent(request)){
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);

                for (FileItem f :
                        fileItems) {
                    if(!f.isFormField()){
                        f.write(new File("E:\\Project\\ssm_title\\src\\main\\webapp\\upload",f.getName()) );
                    }

                }

            }


        }


        return mv;
    }

    @RequestMapping("/store/catalog")
    public ModelAndView storeCatalog(HttpServletRequest request) throws ServletException, IOException, ParseException {
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
            PageInfo pageInfo = catalogService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/store/catalog/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.addObject("courseList",courseService.findAll());
            mv.setViewName("forward:/WEB-INF/pages/store/catalog/add.jsp");
        }else if("save".equals(operation)){
            System.out.println("save");
            Catalog catalog = BeanUtil.fillBean(request, Catalog.class,"yyyy-MM-dd");
            catalogService.save(catalog);
            mv.setViewName("redirect:/store/catalog?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            catalogService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/store/catalog?operation=list");
        }else if("toEdit".equals(operation)){
            String id = request.getParameter("id");
            Catalog catalog = catalogService.findById(id);

            System.out.println("toEdit:"+ id);
            System.out.println(catalog);
            mv.addObject("courseList",courseService.findAll());
            mv.addObject("catalog",catalog);
            mv.setViewName("forward:/WEB-INF/pages/store/catalog/update.jsp?id="+id);
        }else if("edit".equals(operation)){
            String id = request.getParameter("id");
            System.out.println("edit:"+ id);


            Catalog catalog= BeanUtil.fillBean(request, Catalog.class,"yyyy-MM-dd");

            System.out.println(catalog);
            catalogService.update(catalog);
            mv.setViewName("redirect:/store/catalog?operation=list");
        }


        return mv;
    }

    @RequestMapping("/store/course")
    public ModelAndView storeCourse(HttpServletRequest request) throws ServletException, IOException, ParseException {
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
            PageInfo pageInfo = courseService.findAll(page,size);
            request.setAttribute("page",pageInfo);
            System.out.println("list");
            mv.setViewName("forward:/WEB-INF/pages/store/course/list.jsp");
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.setViewName("forward:/WEB-INF/pages/store/course/add.jsp");
        }else if("save".equals(operation)){
            System.out.println("save");
            Course course = BeanUtil.fillBean(request, Course.class,"yyyy-MM-dd");

            courseService.save(course);
            mv.setViewName("redirect:/store/course?operation=list");
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            courseService.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/store/course?operation=list");
        }else if("toEdit".equals(operation)){
            String id = request.getParameter("id");
            Course course = courseService.findById(id);

            System.out.println("toEdit:"+ id);
            System.out.println(course);
            mv.addObject("course",course);
            mv.setViewName("forward:/WEB-INF/pages/store/course/update.jsp?id="+id);
        }else if("edit".equals(operation)){
            String id = request.getParameter("id");
            System.out.println("edit:"+ id);


            Course course = BeanUtil.fillBean(request, Course.class,"yyyy-MM-dd");
            System.out.println(course);
            courseService.update(course);

            mv.setViewName("redirect:/store/course?operation=list");
        }


        return mv;
    }



    @RequestMapping("/store/company")
    public ModelAndView storeCompany(HttpServletRequest request) throws ServletException, IOException, ParseException {
        String operation = request.getParameter("operation");
        ModelAndView mv = new ModelAndView();
        if("list".equals(operation)){
            getList(request,mv);
        }else if("toAdd".equals(operation)){
            System.out.println("toAdd");
            mv.setViewName("forward:/WEB-INF/pages/store/company/add.jsp");
        }else if("save".equals(operation)){
            save(request,mv);
        }else if("delete".equals(operation)){
            String id = request.getParameter("id");
            service.delete(id);
            System.out.println("delete:" + id);
            mv.setViewName("redirect:/store/company?operation=list");
        }else if("toEdit".equals(operation)){
            String id = request.getParameter("id");
            Company company = service.findById(id);

            System.out.println("toEdit:"+ id);
            System.out.println(company);
            mv.addObject("company",company);
//            mv.addObject("id",id);
//            mv.addObject("city",company.getCity());
//            mv.addObject("companySize",company.getCompanySize());
//            mv.addObject("address",company.getAddress());
//
//            mv.addObject("expirationDate",company.getExpirationDate());
//            mv.addObject("industry",company.getIndustry());
//            mv.addObject("name",company.getName());
//            mv.addObject("phone",company.getPhone());
//            mv.addObject("licenseId",company.getLicenseId());
//            mv.addObject("remarks",company.getRemarks());
//            mv.addObject("state",company.getState());
//            mv.addObject("representative",company.getRepresentative());
            mv.setViewName("forward:/WEB-INF/pages/store/company/update.jsp?id="+id);
        }else if("edit".equals(operation)){
            String id = request.getParameter("id");
            System.out.println("edit:"+ id);
            Company company = service.findById(id);
            company.setName(request.getParameter("name"));
            company.setId(id);
            company.setLicenseId(request.getParameter("licenseId"));
            company.setCity(request.getParameter("city"));
            company.setAddress(request.getParameter("address"));
            company.setCompanySize(request.getParameter("companySize"));
            company.setIndustry(request.getParameter("industry"));
            company.setState(Integer.parseInt(request.getParameter("state")));
            company.setRemarks(request.getParameter("remarks"));
            company.setCompanySize(request.getParameter("companySize"));
            company.setPhone(request.getParameter("phone"));
            company.setRepresentative(request.getParameter("representative"));

            System.out.println(company);
            service.update(company);
            mv.setViewName("redirect:/store/company?operation=list");
        }


        return mv;
    }

    public void save(HttpServletRequest request,ModelAndView mv) throws ParseException {
        Company company = new Company();


        company.setName(request.getParameter("name"));


        company.setLicenseId(request.getParameter("licenseId"));
        company.setCity(request.getParameter("city"));
        company.setAddress(request.getParameter("address"));
        company.setCompanySize(request.getParameter("companySize"));
        company.setIndustry(request.getParameter("industry"));
        company.setState("已审核".equals(request.getParameter("state"))?1:0);
        company.setRemarks(request.getParameter("remarks"));
        company.setCompanySize(request.getParameter("companySize"));
        String expirationDate = request.getParameter("expirationDate");
        if (expirationDate.length()> 2){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(expirationDate);
            System.out.println(date);
            company.setExpirationDate(date);
        }

        service.save(company);
        System.out.println("save");
        mv.setViewName("redirect:/store/company?operation=list");
    }
    public void getList(HttpServletRequest request,ModelAndView mv){
        Integer page;
        Integer size;

        String page1 = request.getParameter("page");
        if (page1 == null){
            page = 1;
        }else {
            page = Integer.parseInt(page1);
        }
        String size1 = request.getParameter("size");
        if (size1 == null){
            size = 5;
        }else {
            size = Integer.parseInt(size1);
        }
        PageInfo pageInfo = service.findAll(page,size);
        request.setAttribute("page",pageInfo);
        mv.setViewName("forward:/WEB-INF/pages/store/company/list.jsp");
    }
}
