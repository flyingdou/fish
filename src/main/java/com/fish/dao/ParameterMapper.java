package com.fish.dao;

import com.fish.pojo.Parameter;

public interface ParameterMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKeyWithBLOBs(Parameter record);

}