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

                    题库管理
                    <small>题库审核列表</small>
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
                            <a href="#tab-form" data-toggle="tab">题目审核日志</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/store/examineLog?operation=save" method="post">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">评论</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="评论" name="comments" value="${examineLog.comments}">
                                    </div>

                                    <div class="col-md-2 title">是否有选项</div>
                                    <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="status">
                                            <option value="">请选择</option>
                                            <option value="1" selected>有选项</option>
                                            <option value="-1">无选项</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2 title">题目id</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="题目id" name="questionId" value="${examineLog.questionId}">
                                    </div>
<%--                                    <div class="col-md-2 title">用户id</div>--%>
<%--                                    <div class="col-md-10 data">--%>
<%--                                        <input type="text" class="form-control" placeholder="用户id" name="userId" value="${examineLog.userId}">--%>
<%--                                    </div>--%>


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