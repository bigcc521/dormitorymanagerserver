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
public class Teacherdistrict implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer teacher;

    private Integer district;

    private Integer week;

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }
    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }
    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "Teacherdistrict{" +
            "teacher=" + teacher +
            ", district=" + district +
            ", week=" + week +
        "}";
    }
}
