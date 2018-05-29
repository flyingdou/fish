package com.fish.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 活动接口类
 * 
 * @author Administrator
 *
 */
public interface ActiveService {

	/**
	 * 发布活动
	 * 
	 * @param param
	 * @return
	 */
	JSONObject release(JSONObject param);

	/**
	 * 获取活动列表
	 * 
	 * @param param
	 * @return
	 */
	JSONObject getActiveList(JSONObject param);
	
	/**
	 * 根据id查询活动信息
	 * @param param
	 * @return
	 */
	JSONObject getActiveById(JSONObject param);
}
