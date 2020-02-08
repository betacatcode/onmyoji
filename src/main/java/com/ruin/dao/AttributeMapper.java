package com.ruin.dao;

import com.ruin.domain.Attribute;
import com.ruin.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ruin
 * @date 2020/2/5-21:16
 */

@Repository
public interface AttributeMapper extends MyMapper<Attribute>{


}
