package com.docmall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/admin/*")
@Slf4j
@Controller
public class AdminController {

	
	// 이 주소는 인터셉터를 타지 않음.
	@GetMapping("/intro")
	@ResponseBody   // intro.jsp로 해석되는 것이 아니라 intro라는 문자데이터가 브라우저에 전송된다. 
	public String intro() {
		log.info("called intro....");
		return "intro";
	}
	
	// 이 주소는 인터셉터를 탄다.
	@PostMapping("/admin_ok")
	@ResponseBody
	public String admin_ok() {
		log.info("called admi_ok....");
		return "admi_ok";
	}
	
	// 이 주소는 인터셉터를 탄다.
	@GetMapping("/product/pro_ins")
	@ResponseBody
	public String pro_ins() {
		log.info("/product/pro_ins");
		return "/product/pro_ins";		
	}
}
