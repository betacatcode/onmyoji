package com.ruin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruin.dao.AttributeMapper;
import com.ruin.dao.SoulMapper;
import com.ruin.domain.Attribute;
import com.ruin.domain.Soul;
import com.ruin.service.SoulService;
import com.ruin.util.SoulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ruin
 * @date 2020/2/5-22:11
 */

@Service
public class SoulServiceImpl implements SoulService{

    @Autowired
    SoulMapper soulMapper;
    @Autowired
    AttributeMapper attributeMapper;

    public final Integer pageSize=15;

    @Override
    public PageInfo<Soul> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Soul> souls = soulMapper.selectAllByIdDesc();
        for(Soul soul:souls){
            Attribute attribute=new Attribute();
            attribute.setSoulId(soul.getId());
            soul.setAttributes(attributeMapper.select(attribute));
        }
        PageInfo<Soul> soulPages=new PageInfo(souls);
        return soulPages;
    }

    @Override
    public Soul findById(Long id) {

        Soul soul=soulMapper.selectByPrimaryKey(id);
        Attribute attribute=new Attribute();

        attribute.setSoulId(soul.getId());
        soul.setAttributes(attributeMapper.select(attribute));

        return soul;
    }

    @Override
    public Soul randomSoul() {
        Soul soul = SoulUtil.initSoul(null, null);
        soulMapper.insert(soul);

        Long soulId = soul.getId();
        for (Attribute attribute : soul.getAttributes()) {
            attribute.setSoulId(soulId);
            attributeMapper.insert(attribute);
        }
        return soul;
    }

    @Override
    public void deleteById(Long id) {
        Attribute attribute=new Attribute();
        attribute.setSoulId(id);

        if(id!=null)
            attributeMapper.delete(attribute);

        soulMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Soul designatedSoul(String name, Integer pos) {
        Soul soul = SoulUtil.initSoul(name, pos);
        soulMapper.insert(soul);

        Long soulId = soul.getId();
        for (Attribute attribute : soul.getAttributes()) {
            attribute.setSoulId(soulId);
            attributeMapper.insert(attribute);
        }
        return soul;
    }

    @Override
    public Soul enhanceSoul(Long id, Integer targetLevel) {

        Soul soul=findById(id);
        Soul newSoul=SoulUtil.enhanceSoul(soul, targetLevel);

        soulMapper.updateByPrimaryKey(newSoul);
        List<Attribute>attributes=newSoul.getAttributes();

        for(Attribute attribute:attributes){
            if(attribute.getId()==null){
                attribute.setSoulId(soul.getId());
                attributeMapper.insert(attribute);
            }else
                attributeMapper.updateByPrimaryKey(attribute);
        }

        return newSoul;
    }
}
