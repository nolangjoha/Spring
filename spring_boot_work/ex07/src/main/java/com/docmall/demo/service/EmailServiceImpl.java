package com.docmall.demo.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.docmall.demo.dto.EmailDTO;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

	//EmailConfig.java파일의 javaMailSender()메서드가 스프링 시스템에서 실행되어, 리턴되는 타입의 객체. 
	// 즉 bean을 생성 및 등록작업을 하고, 아래 객체에 주입을 해준다.
	private final JavaMailSender mailSender;
	
	@Override
	public void sendMail(EmailDTO dto, String message) {
		
		//메일 구성정보 담당 객체(받는사람, 보내는 사람, 받는사람 메일주소, 본문내용)
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			//받는 사람의 메일주소
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiverMail()));
			//보내느 사람(메일, 이름)
			mimeMessage.addFrom(new InternetAddress[] {new InternetAddress(dto.getSenderMail(), dto.getSenderName())});
			//제목
			mimeMessage.setSubject(dto.getSubject(), "utf-8");
			//본문내용
			mimeMessage.setText(message, "utf-8");
		
			//메일발송 기능
			mailSender.send(mimeMessage);		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
