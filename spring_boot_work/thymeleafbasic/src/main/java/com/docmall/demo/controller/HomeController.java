package com.docmall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/home/*")
@Slf4j
public class HomeController {

	//기본주소
	@GetMapping("/testA")
	@ResponseBody		// 화면에 return값이 바로 출력된다. 이게 없으면 test.html 파일이 있어야 한다.
	public String testA() {
		return "testA";
	}
	
	//기본주소+쿼리스트링
	@GetMapping("/testB")
	@ResponseBody		
	public String testB(String userid, String pwd) {
		return String.format("testB- userid:%s, pwd:%s", userid, pwd);
	}

	
	//rest full 경로주소
	@GetMapping("/testC/{userid}/{pwd}")
	@ResponseBody
	public String testC(@PathVariable("userid") String userid, @PathVariable("pwd") String pwd) {
		
		return String.format("testC- userid:%s, pwd:%s", userid, pwd);
		
	}
	
		
	//rest full 경로주소 + 쿼리스트링
	@GetMapping("/testD/{userid}")
	@ResponseBody
	public String testD(@PathVariable("userid") String userid, String pwd) {
		
		return String.format("testD- userid:%s, pwd:%s", userid, pwd);
	}
	
	
}
