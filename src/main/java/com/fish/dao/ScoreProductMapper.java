package com.fish.dao;

import com.fish.pojo.ScoreProduct;

public interface ScoreProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ScoreProduct record);

    ScoreProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoreProduct record);

}