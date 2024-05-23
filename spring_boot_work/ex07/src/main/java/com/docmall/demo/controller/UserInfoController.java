package com.docmall.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.service.UserInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/userinfo/*")
//bean 생성 : userInfoService
@RequiredArgsConstructor
@Slf4j
@Controller
public class UserInfoController {
	
	//UserInfoServiceImpl를 사용하지 않고
	//UserInfoService 인터페이스를 사용한 이유는 다형성 목적
	private final UserInfoService userInfoService;
	
	// [회원가입폼]
	@GetMapping("/join")
	public void joinForm() {
		log.info("called joinForm..");
	}
	
	// [아이디 중복체크]
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String u_id) throws Exception {
		
		log.info("아이디: " + u_id);
		
		ResponseEntity<String> entity = null;
		
		String idUse = "";
		
		if(userInfoService.idCheck(u_id) != null) {
			idUse="no";  //사용불가 (null이 아니면 DB에 값이 있는 것으로 사용 불가함.)
		}else {
			idUse = "yes"; //사용 가능(null이어야 DB에 값이 없는 것이므로 사용 가능인것)
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
													//HttpStatus.OK로 200번이 출력된다.
		return entity;
	}
	
	
}
