package com.ruin.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author ruin
 * @date 2020/2/5-21:11
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
