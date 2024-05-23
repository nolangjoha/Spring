package com.docmall.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.demo.dto.EmailDTO;
import com.docmall.demo.service.EmailService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email/*")
public class EmailController {

	private final EmailService emailService;
	
	//스프링이 아래 작업을 자동으로 처리해준다.
	//EmailDTO dto = new EmailDTO dto();
	//dto.setReceiverMail("입력한 메일주소");
	
	@GetMapping("/authcode")
	public ResponseEntity<String> authcode(EmailDTO dto, HttpSession session){

		log.info("dto : " + dto);
		
		ResponseEntity<String> entity = null;
		
		String authcode="";
		for(int i=0; i<6; i++) {
			authcode += String.valueOf((int)(Math.random() * 10));
		}
		
		log.info("인증코드" + authcode);

		// 사용자가 자신의 메일에서 발급받은 인증코드를 읽고, 회원가입시 인증 확인란에 입력을 하게되면, 서버에서 비교목적으로 세션방식으로 인증코드를 메모리에 저장해두어야 한다.
		session.setAttribute("authcode", authcode);
		
		//사용자에게 메일발송(예외발생할 수 있으니까 try catch)
		try {
			emailService.sendMail(dto, authcode);
			entity = new ResponseEntity<String>("success", HttpStatus.OK); //200코드
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); // 500코드
		}
		
		return entity;
	}
}
