package com.ruin.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ruin
 * @date 2020/2/4-12:58
 */
@Table(name="tb_soul")
public class Soul {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Integer position;
    private Integer level=0;
    private List<Attribute> attributes=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Soul{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", level=" + level +
                ", attributes=" + attributes +
                '}';
    }
}
