package com.ruin.domain;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ruin
 * @date 2020/2/4-12:59
 */

@Table(name = "tb_attribute")
public class Attribute {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long soulId;
    private String type;
    private Integer num;

    @Column(name = "attr_value")
    private Double value;

    private Integer isMainAttribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoulId() {
        return soulId;
    }

    public void setSoulId(Long soulId) {
        this.soulId = soulId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getIsMainAttribute() {
        return isMainAttribute;
    }

    public void setIsMainAttribute(Integer isMainAttribute) {
        this.isMainAttribute = isMainAttribute;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", soulId=" + soulId +
                ", type='" + type + '\'' +
                ", num=" + num +
                ", value=" + value +
                ", isMainAttribute=" + isMainAttribute +
                '}';
    }
}
