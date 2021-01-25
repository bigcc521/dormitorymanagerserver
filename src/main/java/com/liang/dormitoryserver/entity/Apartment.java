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
public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String airconditioner;

    private String lavatory;

    private String sewer;

    private Integer teachermanager;

    private Integer selfmanage;

    private Integer district;

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
    public String getAirconditioner() {
        return airconditioner;
    }

    public void setAirconditioner(String airconditioner) {
        this.airconditioner = airconditioner;
    }
    public String getLavatory() {
        return lavatory;
    }

    public void setLavatory(String lavatory) {
        this.lavatory = lavatory;
    }
    public String getSewer() {
        return sewer;
    }

    public void setSewer(String sewer) {
        this.sewer = sewer;
    }
    public Integer getTeachermanager() {
        return teachermanager;
    }

    public void setTeachermanager(Integer teachermanager) {
        this.teachermanager = teachermanager;
    }
    public Integer getSelfmanage() {
        return selfmanage;
    }

    public void setSelfmanage(Integer selfmanage) {
        this.selfmanage = selfmanage;
    }
    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Apartment{" +
            "id=" + id +
            ", name=" + name +
            ", airconditioner=" + airconditioner +
            ", lavatory=" + lavatory +
            ", sewer=" + sewer +
            ", teachermanager=" + teachermanager +
            ", selfmanage=" + selfmanage +
            ", district=" + district +
        "}";
    }
}
