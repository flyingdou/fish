package com.fish.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.ActiveService;

/**
 * 活动controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/active")
public class ActiveController {

	@Autowired
	private ActiveService activeService;

	/**
	 * 发布活动
	 * 
	 * @param json
	 * @return String
	 */
	@RequestMapping("/release")
	@ResponseBody
	public String release(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = activeService.release(param);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 获取活动列表
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getActiveList")
	@ResponseBody
	public String getActiveList(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = activeService.getActiveList(param);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 根据id查询活动信息
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getActiveById")
	@ResponseBody
	public String getActiveById(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = activeService.getActiveById(param);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}
}
