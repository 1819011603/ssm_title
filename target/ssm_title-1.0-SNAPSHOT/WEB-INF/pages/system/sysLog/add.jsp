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
                系统管理
                <small>系统日志管理</small>
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
                            <a href="#tab-form" data-toggle="tab">编辑系统日志</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/system/sysLog?operation=save" method="post">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">用户名</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="用户名" name="userName" value="${sysLog.userName}">
                                    </div>
                                    <div class="col-md-2 title">ip</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="ip" name="ip" value="${sysLog.ip}">
                                    </div>
                                    <div class="col-md-2 title">访问时间</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="访问时间" name="time" value="${sysLog.time}">
                                    </div>
                                    <div class="col-md-2 title">方法</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="方法" name="method" value="${sysLog.method}">
                                    </div>
                                    <div class="col-md-2 title">操作</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="操作" name="action" value="${sysLog.action}">
                                    </div>

                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>

</html>