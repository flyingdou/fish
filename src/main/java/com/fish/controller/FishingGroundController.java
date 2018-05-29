package com.fish.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.FishingGroundService;

@Controller
@RequestMapping("/fishingGround")
public class FishingGroundController {

	@Autowired
	private FishingGroundService fishingGroundService;

	@RequestMapping("/release")
	@ResponseBody
	public String release(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = fishingGroundService.release(param);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}
}
