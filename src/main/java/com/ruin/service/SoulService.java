package com.ruin.service;

import com.github.pagehelper.PageInfo;
import com.ruin.domain.Soul;


/**
 * @author ruin
 * @date 2020/2/5-22:10
 */
public interface SoulService {
    public PageInfo<Soul> findAll(Integer pageNum);

    public Soul findById(Long id);

    public Soul randomSoul();

    public void deleteById(Long id);

    public Soul designatedSoul(String name,Integer pos);

    public Soul enhanceSoul(Long id,Integer targetLevel);
}
