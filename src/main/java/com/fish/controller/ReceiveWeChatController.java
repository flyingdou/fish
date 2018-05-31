package com.fish.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/receiveWeChat")
public class ReceiveWeChatController {

	@RequestMapping("/receive")
	@ResponseBody
	public String receive(HttpServletRequest request) {
//		Map<String, Object> param = commentsUtil.getInputStreamXMLToMap(request);
	
		// 返回响应结果
		JSONObject result = new JSONObject();
		result.fluentPut("success", true);
		return JSON.toJSONString(result);
	}
}
