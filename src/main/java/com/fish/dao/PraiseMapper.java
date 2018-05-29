package com.fish.dao;

import com.fish.pojo.Praise;

public interface PraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Praise record);

}