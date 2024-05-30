package com.docmall.demo.config;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import jakarta.servlet.MultipartConfigElement;

/*
스프링부트 2.7에서는 multipart가 기본 bean으로 등록되어 있다.
스프링부트 3 이상부터는 multipart 설정 클래스를 생성하여, bean으로 등록해야 한다.

*/
@Configuration
public class MultipartConfig {

	//스프링에서 자동으로 관리. 리턴타입 muptipartResolver(의존성주입DI) bean등록 및 관리
	@Bean    //라이브러리에서 제공하는 클래스를 스프링에서 관리.
	public MultipartResolver muptipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	/*
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(C:\\Dev\\upload\\temp);
		factory.setMaxRequestSize(30MB);
		factory.setMaxFileSize(10MB);
		
		return factory.createMultipartConfig();
		
	} 
	*/
	
}
