package com.docmall.demo.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.docmall.demo.dto.EmailDTO;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

	//EmailConfig.java파일의 javaMailSender()메서드가 스프링 시스템에서 실행되어, 리턴되는 타입의 객체. 
	// 즉 bean을 생성 및 등록작업을 하고, 아래 객체에 주입을 해준다.
	private final JavaMailSender mailSender;
	
	//타임리프 템플릿 객체가 자동으로 제공해줌.
	private final SpringTemplateEngine templateEngine;
	
	@Override
	public void sendMail(String type, EmailDTO dto, String authcode) {
		
		//메일 구성정보 담당 객체(받는사람, 보내는 사람, 받는사람 메일주소, 본문내용)
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			//받는 사람의 메일주소
			//mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiverMail()));
			//보내느 사람(메일, 이름)
			//mimeMessage.addFrom(new InternetAddress[] {new InternetAddress(dto.getSenderMail(), dto.getSenderName())});
			//제목
			//mimeMessage.setSubject(dto.getSubject(), "utf-8");
			//본문내용
			//mimeMessage.setText(authcode, "utf-8");
		
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			mimeMessageHelper.setTo(dto.getReceiverMail()); // 메일 수진자
			mimeMessageHelper.setFrom(new InternetAddress(dto.getSenderMail(), dto.getSenderName()));
			mimeMessageHelper.setSubject(dto.getSubject()); //메일제목
			mimeMessageHelper.setText(setContext(authcode, type), true); //메일본문내용, HTML여부
															//"email"은 email.html파일을 가리킨다.
			//메일발송 기능s
			mailSender.send(mimeMessage);		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//인증번호 및 임시 비밀번호 생성 메서드
	//public String createCode() {}
		
	//thymeleaf를 통한 html적용
	//String code: 인증코드 String type : email.html
	public String setContext(String authcode, String type) {
		Context context = new Context(); //context객체는 타임리프제공 클래스
		context.setVariable("authcode", authcode);
		return templateEngine.process(type, context);
	}
	
		
		
	
	
	
	
}
