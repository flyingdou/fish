package com.fish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/receiveWeChat")
public class ReceiveWeChatController {

	@RequestMapping("/receive")
	@ResponseBody
	public String receive(String echostr) {
		return echostr;
	}
}
