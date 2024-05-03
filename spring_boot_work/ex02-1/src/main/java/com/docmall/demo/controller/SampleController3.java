package com.docmall.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sample3/*")
@Controller
public class SampleController3 {

	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);	

	//클라이언트에서 사용하는 파라미터 명과 스프링의 메서드 파라미터명이 일치하지 않을 때 중간에 @RequestParm("클라이언트 파라미터명") 사용
	//매핑주소 
	
	@GetMapping("doA")
	public void doA(@RequestParam("uname") String name, @RequestParam("uage") int age) {
		logger.info("이름:" + name);
		logger.info("나이:"+ age);
	}
	/*
	@GetMapping("doB")
	public void doB(@RequestMapping(value="name", required=false, defaultValue="이름없음")	String name, int age) {
		logger.info("이름:"+ name);
		logger.info("나이:"+ age);
	}
*/
	@GetMapping("doD")
	public void doD(	@RequestParam("num")	ArrayList<Integer> idx) {
		logger.info("idx:"+ idx);
	}
	
	@GetMapping("doE")
	public void doE(@RequestParam("num")int[] idx) {
		logger.info("idx: "+ Arrays.toString(idx));
	}
	
	@GetMapping("doF")
	public void doF(	@RequestParam("userId")	ArrayList<String> userId) {
		logger.info("idx: "+ userId);
	}
	
	
	
	
}
