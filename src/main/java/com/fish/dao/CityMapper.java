package com.fish.dao;

import com.fish.pojo.City;

public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

}