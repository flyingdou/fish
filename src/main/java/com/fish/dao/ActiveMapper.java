package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.Active;

/**
 * 活动表数据操作接口类
 * 
 * @author Administrator
 *
 */
public interface ActiveMapper {

	/**
	 * 添加一条活动数据
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(Active record);

	/**
	 * 通过id修改活动数据
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Active record);

	/**
	 * 通过id删除活动数据
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 通过id查询活动数据
	 * 
	 * @param id
	 * @return
	 */
	Active selectByPrimaryKey(Integer id);

	/**
	 * 根据城市查询活动列表
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getActiveListByCity(Map<String, Object> queryParam);

	/**
	 * 根据推荐查询活动列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getActiveListByRecommend(Map<String, Object> queryParam);

	/**
	 * 根据id查询活动信息
	 * 
	 * @return
	 */
	Map<String, Object> getActiveById(String activeId);

}