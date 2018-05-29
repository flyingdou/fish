package com.fish.dao;

import com.fish.pojo.CashAccount;

public interface CashAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CashAccount record);

    CashAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashAccount record);

}