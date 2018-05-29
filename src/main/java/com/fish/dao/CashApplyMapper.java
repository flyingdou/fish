package com.fish.dao;

import com.fish.pojo.CashApply;

public interface CashApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CashApply record);

    CashApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashApply record);

}