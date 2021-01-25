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
public class Tphoto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String url;

    private Integer tgrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getTgrade() {
        return tgrade;
    }

    public void setTgrade(Integer tgrade) {
        this.tgrade = tgrade;
    }

    @Override
    public String toString() {
        return "Tphoto{" +
            "id=" + id +
            ", url=" + url +
            ", tgrade=" + tgrade +
        "}";
    }
}
