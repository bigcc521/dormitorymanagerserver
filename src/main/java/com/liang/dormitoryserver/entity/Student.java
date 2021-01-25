package com.liang.dormitoryserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String password;

    private Integer dormitory;

    private Integer role;

    private Integer selfmanage;

    private Integer college;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getDormitory() {
        return dormitory;
    }

    public void setDormitory(Integer dormitory) {
        this.dormitory = dormitory;
    }
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
    public Integer getSelfmanage() {
        return selfmanage;
    }

    public void setSelfmanage(Integer selfmanage) {
        this.selfmanage = selfmanage;
    }
    public Integer getCollege() {
        return college;
    }

    public void setCollege(Integer college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", name=" + name +
            ", password=" + password +
            ", dormitory=" + dormitory +
            ", role=" + role +
            ", selfmanage=" + selfmanage +
            ", college=" + college +
        "}";
    }
}
