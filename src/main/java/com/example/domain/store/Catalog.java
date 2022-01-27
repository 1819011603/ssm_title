package com.example.domain.store;

import java.util.Date;

/**
 * @author 18190
 * @Date: 2021/7/16  22:40
 * @VERSION 1.0
 *
 *
 * 1Course 对应 多个Catalog
 */

public class Catalog {
    private String id;
    private String name;
    private String remark;
    private String state;
    private String courseId;
    private Date createTime;
    private Course course;

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Catalog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                ", courseId='" + courseId + '\'' +
                ", createTime=" + createTime +
                ", course=" + course +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
