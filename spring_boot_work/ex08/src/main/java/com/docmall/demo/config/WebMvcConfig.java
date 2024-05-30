package com.docmall.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.docmall.demo.interceptor.LoginInterceptor;

import lombok.RequiredArgsConstructor;

// 용도? : LoginInterceptor.java 인터셉터 클래스를 위한 매핑주소 설정

@RequiredArgsConstructor
@Component  //bean생성
public class WebMvcConfig implements WebMvcConfigurer{

	public final LoginInterceptor loginInterceptor;

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/userinfo/mypage", "/userinfo/changepw", "/userinfo/delete"); 
				//addPathPatterns("매핑주소설정", "매핑주소설정") //갯수 상관없음
	}
	
	
	

	
	
	
}
