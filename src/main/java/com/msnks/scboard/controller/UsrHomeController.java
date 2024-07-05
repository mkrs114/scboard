package com.msnks.scboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {

	
	// 액션 메서드 시작
	@RequestMapping("/usr/home/main")
	public String getString() {
		return "usr/home/main";
	}
	
	// 액션 메서드 시작
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
}
