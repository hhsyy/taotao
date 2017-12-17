package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/page/register")
	public String showRegster(){
		return "register";
	}
	
	@RequestMapping("/page/login")
	public String showLogin(Model model,String redirect){
		model.addAttribute("redirect",redirect);
		return "login";
	}
}
