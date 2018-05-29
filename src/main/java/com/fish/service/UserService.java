package com.fish.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author dou
 * 用户service层
 */
public interface UserService {
	
	/**
	 * 微信登录
	 * @param param
	 * @return
	 */
	public JSONObject wechatLogin (JSONObject param) throws Exception;

}
