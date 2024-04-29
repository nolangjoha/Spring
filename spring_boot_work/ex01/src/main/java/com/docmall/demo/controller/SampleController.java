package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller    //클라이언트(브라우저)로 부터 요청을 받아 실행하는 스프링클래스는 @Controller 어노테이션을 클래스 수준으로 적용해야 한다.
public class SampleController {

	// 작업 및 에러등을 변수들에 대한 값 확인을 통해 추적, (주의)클래스 이름과 괄호속 이름과 타입명이 일치해야 함.)
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	//클라이언트(브라우저)에서 메서드를 호출하고자 할 경우에는 매핑주소를 설정해야한다. 이때 매핑주소를 설정하는 어노테이션이 @RequestMapping이다.
	@RequestMapping("/doA")  //WEB-INF/views/ + 메서드명 + .jsp    // 매핑주소가 jsp파일명으로 사용되었다.
	public void doA() {
		logger.info("doA called...");
	}
	
	//매핑주소 /doB와 메서드명 doB()는 이름은 상관없다(일치할 필요 없다)
	@RequestMapping("/doB")   
	public void doB() {
		//System.out.println("doB called..."); //성능상의 이유로 사용하지 않는다.
		logger.info("doB called...");
	}
	
	@RequestMapping("/doC")
	public void doC() {
		logger.info("doC called...");
	}
	
	
	
}
