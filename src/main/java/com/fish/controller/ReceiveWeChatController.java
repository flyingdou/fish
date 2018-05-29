package com.fish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/receiveWeChat")
public class ReceiveWeChatController {

	@RequestMapping("/receive")
	public String receive() {

		return null;
	}
}
