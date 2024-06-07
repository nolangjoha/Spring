package com.docmall.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/basic2/*")
@Controller
public class BasicController2 {

	   // th:block 예제
	   @GetMapping("/ex01")
	   public void ex01(Model model) {
	      List<User> list = new ArrayList<>();
	      list.add(new User("userA", 10));
	      list.add(new User("userB", 20));
	      list.add(new User("userC", 30));
	      
	      model.addAttribute("users", list); // users객체 3개
	   }


		
		// 자바스크립트 예제
		@GetMapping("/ex02")
		public void ex02(Model model) {
		
			model.addAttribute("user", new User("user01", 40));
		}

		//기본객체.
		/*
		 스프링3.0 이전 지원됐으나 3.0부터는 지원 안함.  >> 3.0에서 일엏게 쓰면 에러 발생(보안이슈로 인해 개편했다함.)
		 ${#request} : 클라이언트의 요청정보를 갖고 있는 객체(클라이언트가 입력했던 데이터, ip, 클라이언트가 사용한 브라우저 등) // 서버내 리퀘스트 객체가 갖고 있는다. 
		 ${#response} : 서버에서 응답하는 응답객체
		 ${#session}
		 ${#servletContext}

		 스프링 2.x or 3.x버전 지원
		 ${#locale} 
		 
		 
		 위의 내장객체를 3.0이후에도 사용하고 싶은 경우 model객체로 추가작업을 한다.
		 참고 : 메서드의 리턴타입은 String
		 */
		@GetMapping("/ex03")
		public String ex03(Model model, HttpServletRequest request, HttpServletResponse response) {
			model.addAttribute("request", request);
			model.addAttribute("response", response);
			model.addAttribute("servletContext", request.getServletConnection());
		
			return "/basic2/ex03";
		}
		
		
		//param객체, session객체, bean객체 사용
		// param객체 사용 URL. /basic2/ex04?userid=doccomsa
		@GetMapping("/ex04") 
		public String ex04(HttpSession session) {
			session.setAttribute("login_status", "user01");
			return "/basic2/ex04";
		}
		
		//bean객체 사용 전역적인 기능지원
		@Component("helloBean") 
		static class HelloBean{
			public String hello(String data) {
				return "Hello " + data;
			}
		}
	
		
		// thymeleaf 유틸리티객체- temporals : 자바8 날짜 포맷지원.
		// 날짜 데이터. 1)Date, 2)Calendar, 3)LocalDateTime, LocalDate, LocalTime
		@GetMapping("/ex05")
		public String ex05(Model model) {
			model.addAttribute("localDateTime", LocalDateTime.now());
			return "/basic2/ex05";
		}
		
		// thymeleaf 유틸리티객체- numbers
		@GetMapping("/ex06")
		public String ex06(Model model) {
			
			model.addAttribute("number", 5);
			return "/basic2/ex06";
		}
		
		
}
