package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.demo.domain.ProductVO;

/*
 클라이언트의 결과값을 jsp로 동작하여 받고자 하는 것이 아니라, JSON포맷의 데이터로 받고자 할때 학습예제
 @ResponseBody 사용 
 */


@Controller
public class SampleController6 {

	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(SampleController6.class); 

	//public '클래스명'
	// ProductVO vo객체를 JSON으로 변환할때 사용하는 라이브러리 : jackson-databind 라이브러리.
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON() {
		
		//ProductVO객체 생성
		ProductVO vo = new ProductVO("사과", 10000);
		
		logger.info("called doJSON..." + vo);  //vo.toString()호출
		
		//return null; //참조값은 초기값을 null로 가질 수 있다. null을 입력하여 우선 자바의 문법을 완성시키자.
		return vo; //객체를 생성하고 타입을 맞춘 리턴값을 넣어준다.			  
				   // {"name":"사과","price":10000.0} JSON문자열이 클라이언트(브라우저)로 응답된다.
	}
	
	
	
}
