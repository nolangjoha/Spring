package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);
	
	//메핑주소와 메서드 명은 일치할 필요 없다.
	//(규칙)매핑주소를 jsp 파일 명으로 사용할 경우에는 메서드의 리턴타입이 void여야 한다.
	//(규칙)매핑주소를 jsp 파일 명으로 사용하지 않을 경우에는 메서드의 리턴타입을 String로해서 리턴값을 jsp명으로 사용한다.
	
	@RequestMapping("/doD")
	public String doD() {
		logger.info("called doD....");
		return "testD"; //return "jsp파일명";
						//"testD"가 jsp파일명이다.
	}
	
	@RequestMapping("/doE")
	public String doE() {
		logger.info("called doE....");
		return "testE";  //"testE"가 jsp파일명이다.
	}
	
	@RequestMapping("/doF")
	public String doF() {
		logger.info("called doF....");
		return "testF";
	}
	
	
	
}
