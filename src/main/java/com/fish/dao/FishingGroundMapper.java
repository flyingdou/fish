package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.FishingGround;

public interface FishingGroundMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(FishingGround record);

	FishingGround selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(FishingGround record);

	/**
	 * 通过id列表查询poi_id列表
	 * 
	 * @param ids
	 * @return
	 */
	List<String> getPoiIdListByIdList(String ids);

	/**
	 * 通过钓场id列表查询钓场列表信息
	 * 
	 * @param ids
	 * @return
	 */
	List<Map<String, Object>> getFishingGroundListByIdList(String ids);

	/**
	 * 查询钓场列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getFishingGroundList(Map<String, Object> queryParam);
}