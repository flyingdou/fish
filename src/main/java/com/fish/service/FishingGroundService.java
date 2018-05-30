package com.fish.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 钓场业务接口
 * 
 * @author Administrator
 *
 */
public interface FishingGroundService {

	/**
	 * 发布钓场
	 * 
	 * @return
	 */
	public JSONObject release(JSONObject param);

	/**
	 * 查询钓场列表
	 * 
	 * @return
	 */
	public JSONObject getFishingGroundList(JSONObject param);
}
