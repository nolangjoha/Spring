package com.docmall.demo.controller;

import org.slf4j.Logger;			//복붙
import org.slf4j.LoggerFactory;		//복붙
import org.springframework.stereotype.Controller;		//복붙
import org.springframework.web.bind.annotation.RequestMapping;		//복붙

@Controller
public class SampleController3 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);//복붙
	
	//?뒤 문자열을 쿼리스트링이라고 한다.
	//http://localhost:9090/doG?name=abc&age=100  //결과 : name=abc age=a00
	//http://localhost:9090/doG?age=100  //결과 : name = null age=100
	//http://localhost:9090/doG?name=abc  //결과 : 오류

		@RequestMapping("/doG")
	public String doG(String name, int age) {
		
		logger.info("이름은?"+ name);
		logger.info("나이는?"+ age);
		
		return "testG";
	}
	
}
