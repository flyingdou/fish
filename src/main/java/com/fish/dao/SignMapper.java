package com.fish.dao;

import com.fish.pojo.Sign;

public interface SignMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sign record);

}