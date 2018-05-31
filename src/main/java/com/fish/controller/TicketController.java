package com.fish.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.service.TicketService;

/**
 * 卡券业务controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	/**
	 * 发布垂钓券
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/release")
	@ResponseBody
	public String release(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = ticketService.release(param);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 查询卡券列表
	 * 
	 * @return
	 */
	@RequestMapping("/getTicketList")
	@ResponseBody
	public String getTicketList() {
		try {
			JSONObject result = ticketService.getTicketList();
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 通过id查询卡券详情
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/getTicketById")
	@ResponseBody
	public String getTicketById(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = ticketService.getTicketById(param);
			return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 领取卡券
	 * 
	 * @param json
	 * @return
	 */
	@RequestMapping("/addCard")
	@ResponseBody
	public String addCard(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = ticketService.addCard(param);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

	/**
	 * 核销卡券
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/consume")
	@ResponseBody
	public String consume(String json) {
		try {
			JSONObject param = JSONObject.parseObject(URLDecoder.decode(json, "UTF-8"));
			JSONObject result = ticketService.consume(param);
			return JSON.toJSONString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(e);
		}
	}

}
