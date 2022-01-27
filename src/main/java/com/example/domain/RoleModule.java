package com.example.domain;

/**
 * @author 18190
 * @Date: 2021/7/19  22:23
 * @VERSION 1.0
 */
public class RoleModule {
    private Integer id;
    private Integer pId;
    private String name;
    private Boolean checked;

    @Override
    public String toString() {
        return "RoleModule{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", checked=" + checked +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
