package com.fish.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Json字符串转Bean
 *
 */
public class JSON2Bean {

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object json2City (String jsonStr, Class clazz) {
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		return jsonObject.toJavaObject(clazz);
	}
	
	
	
	
}
