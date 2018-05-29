package com.fish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/wechatLogin")
	@ResponseBody
	public String wechatLogin (String json) {
		JSONObject ret = new JSONObject();
		try {
			// 处理请求参数
			JSONObject param = JSONObject.parseObject(json);
			ret = userService.wechatLogin(param);
			
		} catch (Exception e) {
			ret.fluentPut("success", false)
			   .fluentPut("message", e.toString())
			   ;
		}
		
		return JSON.toJSONString(ret);
	}

	
}
