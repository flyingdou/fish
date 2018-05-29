package com.fish.dao;

import com.fish.pojo.Exchange;

public interface ExchangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Exchange record);

    Exchange selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exchange record);

}