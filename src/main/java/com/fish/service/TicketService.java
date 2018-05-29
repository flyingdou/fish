package com.fish.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 卡券业务接口
 * 
 * @author Administrator
 *
 */
public interface TicketService {

	/**
	 * 发布垂钓券
	 * 
	 * @param param
	 * @return
	 */
	JSONObject release(JSONObject param);

	/**
	 * 查询垂钓券列表
	 * 
	 * @param param
	 * @return
	 */
	JSONObject getTicketList();

	/**
	 * 通过Id查询垂钓券
	 * 
	 * @param param
	 * @return
	 */
	JSONObject getTicketById(JSONObject param);
}
