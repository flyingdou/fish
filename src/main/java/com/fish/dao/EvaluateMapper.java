package com.fish.dao;

import com.fish.pojo.Evaluate;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Evaluate record);

}