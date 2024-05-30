package com.docmall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/thymeleaf/*")
public class TestController {

	@GetMapping("/test")
	public String test() {
		
		log.info("called test.....");
		
		return "/thymeleaf/test"; //뷰이름(vies name)이 jsp or thymeleaf 둘중 어떤걸로 해석되느냐?
	}
	
}
