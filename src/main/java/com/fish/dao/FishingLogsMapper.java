package com.fish.dao;

import com.fish.pojo.FishingLogs;

public interface FishingLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(FishingLogs record);

    FishingLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FishingLogs record);

}