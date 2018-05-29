package com.fish.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

/**
 * 
 * @author dou
 * 登录控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	
	
	@RequestMapping("/wechatLogin")
	@ResponseBody
	public String wechatLogin(String code, HttpServletRequest request, HttpServletResponse response) {
		JSONObject ret = new JSONObject();
		try {
			ret.accumulate("success", true).accumulate("message", "dou");
						
		} catch (Exception e) {
			ret.accumulate("success", false).accumulate("message", e.toString());
		}
		
		return ret.toString();
	}

}
