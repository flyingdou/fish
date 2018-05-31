package com.fish.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/receiveWeChat")
public class ReceiveWeChatController {

	@RequestMapping("/receive")
	public void receive(String echostr, HttpServletResponse response) {
		try {
			response.getWriter().write(echostr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
