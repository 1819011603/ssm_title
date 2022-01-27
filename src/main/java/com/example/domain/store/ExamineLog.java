package com.example.domain.store;

import java.util.Date;

/**
 * @author 18190
 * @Date: 2021/7/22  18:14
 * @VERSION 1.0
 */
public class ExamineLog {
    private String id;
    private String comments;
    private String status;
    private String questionId;
    private String userId;
    private Date createTime;
    private String createBy;
    private String createDept;
    private String updateBy;
    private Date updateTime;

    @Override
    public String toString() {
        return "ExamineLog{" +
                "id='" + id + '\'' +
                ", comments='" + comments + '\'' +
                ", status='" + status + '\'' +
                ", questionId='" + questionId + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", createDept='" + createDept + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    public Date getUpdateTime() {
        return updateTime;
    }
}
