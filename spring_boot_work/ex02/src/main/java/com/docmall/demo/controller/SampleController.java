package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.dto.LoginDTO;

@RequestMapping("/sample/*") // sample폴더가 만들어져야 한다.//굵직한 기능별로 폴더를 생성할 것이다. 
				 	 	 	 // 아래 매핑주소의 공통주소이면서, jsp파일의 폴더명이 된다.
@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	/*
	public class SampleController수준에 @RequestMapping("/sample/*")가 없을때
	아래와 같이 작성한다.
	
	//클라이언트(브라우저) 요청이 get방식일 경우 사용. 주소? localhost:9090/sample/doA
	@GetMapping("/sample/doA")
	public void doA() {
		logger.info("called doA");
	}
	
	@GetMapping("/sample/doB")  // 주소? localhost:9090/sample/doB
	public void doB() {
		logger.info("called doB....");
	}
	
	@GetMapping("/sample/doC")  // 주소? localhost:9090/sample/doC
	public void doC() {
		logger.info("called doC....");
	}
	*/
	
	//클라이언트(브라우저) 요청이 get방식일 경우 사용. 주소? localhost:9090/sample/doA
	@GetMapping("doA")
	public void doA() {
		logger.info("called doA.....");
	}
	
	@GetMapping("doB")  // 주소? localhost:9090/sample/doB
	public void doB() {
		logger.info("called doB....");
	}
	
	@GetMapping("doC")  // 주소? localhost:9090/sample/doC
	public void doC() {
		logger.info("called doC....");
	}
	
	// doD()메서드를 여러개의 매핑주소가 공유하고자 할 때 다음과 같이 처리한다.
	//주소? localhost:9090/sample/doD
	//주소? localhost:9090/sample/testD	
	@GetMapping(value = {"doD","testD"})  
	public void doD() {
		logger.info("called doD....");
	}
		
	/***************************************************************/
	
	//주소? localhost:9090/sample/basicGet (get요청방식)
	@GetMapping("basicGet")  
	public void basicGet() {
		logger.info("called basicGet....");
	}
	
	//주소? localhost:9090/sample/basicPost
	//위 주소는 주소창에 직접작성하는것이 아니라 태그로 불러와져야 한다.
	/*
	@PostMapping("basicPost")
	public void basicPost(String u_id, String u_pw) {
		logger.info("called basicPost....");
		logger.info("아이디는 :" + u_id);
		logger.info("비밀번호는 :" + u_pw);	
	}
	*/
	
	@PostMapping("basicPost")
	public void basicPost(LoginDTO dto) {
		logger.info("called basicPost....");
		logger.info("아이디는 :" + dto.getU_id());
		logger.info("비밀번호는 :" + dto.getU_pw());
		
	}
	
	

}
