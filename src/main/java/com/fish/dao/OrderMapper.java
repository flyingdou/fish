package com.fish.dao;

import com.fish.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

}