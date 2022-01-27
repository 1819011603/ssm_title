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
                <small>会员管理管理</small>
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
                            <a href="#tab-form" data-toggle="tab">会员管理新建</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/front/memberQuestion?operation=save" method="post">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">题目id</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="题目id" name="questionId" value="${memberQuestion.questionId}">
                                    </div>
                                    <div class="col-md-2 title">试卷id</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="试卷id" name="examinationpaperId" value="${memberQuestion.examinationpaperId}">
                                    </div>
                                    <div class="col-md-2 title">题目结果</div>

                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="" name="answerResult" value="${memberQuestion.answerResult}">
                                    </div>
                                    <div class="col-md-2 title">正确答案</div>
                                    <div class="col-md-10 data">

                                            <input type="text" placeholder=""  name="rightAnswer" class="form-control pull-right"
                                                   value="${memberQuestion.rightAnswer}" id="datepicker">

                                    </div>
                                    <br>
                                    <br>


                                </div>
                            </div>

                            <div class="box-tools text-center">
                                <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>


</script>
</html>