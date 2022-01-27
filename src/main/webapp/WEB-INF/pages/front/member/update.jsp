<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                会员管理
                <small>会员账号管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">会员账号编辑</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/front/member?operation=edit" method="post">
                            <input type="hidden" name="id" value="${member.id}">
                            <div class="col-md-2 title">用户名</div>
                            <div class="col-md-10 data">
                                <input type="text" class="form-control" placeholder="用户名" name="nickName" value="${member.nickName}">
                            </div>
                            <div class="col-md-2 title">密码</div>
                            <div class="col-md-10 data">
                                <input type="text" class="form-control" placeholder="密码" name="password" value="${member.password}">
                            </div>
                            <div class="col-md-2 title">性别</div>
                            <div class="col-md-10 data line-height36">
                                <select class="form-control" name="gender">

                                    <option value="male" ${course.gender eq "male" ? 'selected':''}>male</option>
                                    <option value="female" ${course.gender == "female" ? 'selected':''}>female</option>
                                </select>
                            </div>
                            <div class="col-md-2 title">年龄</div>
                            <div class="col-md-10 data">
                                <input type="text" class="form-control" placeholder="" name="age" value="${member.age}">
                            </div>
                            <div class="col-md-2 title">出生时间</div>
                            <div class="col-md-4 data">
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" placeholder="出生时间"  class="form-control pull-right"
                                           value="${member.birthday}" id="datepicker">
                                </div>
                            </div>
                            <br>
                            <div class="col-md-2 title">邮箱</div>
                            <div class="col-md-10 data">
                                <input type="text" class="form-control" placeholder="邮箱" name="email" value="${member.email}">
                            </div>
                            <div class="col-md-2 title">电话号码</div>
                            <div class="col-md-10 data">
                                <input type="text" class="form-control" placeholder="邮箱" name="telephone" value="${member.telephone}">
                            </div>
                            <div class="col-md-2 title">地址</div>
                            <div class="col-md-10 data">
                                <input type="text" class="form-control" placeholder="地址" name="address" value="${member.address}">
                            </div>

                            <div class="col-md-2 title">评论</div>
                            <div class="col-md-10 data">
                                <input type="text" class="form-control" placeholder="评论" name="remark" value="${member.remark}">
                            </div>
                            <div class="col-md-2 title">状态</div>
                            <div class="col-md-10 data line-height36">
                                <select class="form-control" name="state">

                                    <option value="0" ${course.state == "0" ? 'selected':''}>0</option>
                                    <option value="1" ${course.state == "1" ? 'selected':''}>1</option>
                                </select>
                            </div>


                        </form>

                    </div>
                </div>
            </div>

        </section>
        <div class="box-tools text-center">
        <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
    </div>
    </div>
</body>
<script src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
</html>