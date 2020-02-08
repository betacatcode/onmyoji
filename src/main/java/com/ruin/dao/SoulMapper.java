package com.ruin.dao;

import com.ruin.domain.Soul;
import com.ruin.mapper.MyMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ruin
 * @date 2020/2/5-21:16
 */

@Repository
public interface SoulMapper extends MyMapper<Soul>{

    @Select("SELECT * FROM tb_soul ORDER BY id DESC")
    public List<Soul> selectAllByIdDesc();
}
