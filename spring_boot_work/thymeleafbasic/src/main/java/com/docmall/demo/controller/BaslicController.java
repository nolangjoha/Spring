package com.docmall.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.User;

@RequestMapping("/basic/*")
@Controller
public class BaslicController {

	
	// data : 태그가 포함된 텍스트 문자열
	@GetMapping("/doA")
	public String doA(Model model) {
		
		model.addAttribute("data", "hello <b> Spring!</b>");  //이걸 doA에서 사용하기 위해서 doA.html을 타임리프로 사용할 수있도록 설정해줘야 한다.
		return "/basic/doA";  //타임리프 파일명.
	}
	
	//user객체를 출력하는 용도- 회원정보 1개
	@GetMapping("/doB")
	public String doB(Model model) {
		User userHong = new User("홍길동", 100);  // 회원정보
		model.addAttribute("user", userHong);
		return "/basic/doB";
	}
	
	//list 객체를 출력하는 용도- 회원목록 
	@GetMapping("/doC")
	public String doC(Model model) {
		User userSon = new User("손흥민", 33);
		User userLee = new User("이강인", 25);
		User userKim = new User("김민재", 28);
		User userHwa = new User("황희찬", 27);

		/*
		List<User> list = new ArrayList<>();
		//리스트에 객체를 추가하는 방법 1
		list.add(userSon);
		list.add(userLee);
		list.add(userKim);
		list.add(userHwa);
		*/
		
		//리스트에 객체를 추가하는 방법 2
		List<User> list = new ArrayList<>(Arrays.asList(userSon,userLee, userKim, userHwa));
		
		model.addAttribute("list", list);
		
		return "/basic/doC";
	}
	
	
	//Map객체를 출력
	@GetMapping("/doD")
	public String doD(Model model) {
		Map<String, User> map = new HashMap<>();
		map.put("userA", new User("손흥민", 33));
		map.put("userB", new User("이강인", 25));

		model.addAttribute("userMap", map);
		
		return "/basic/doD";   // 타임리프 파일명. /basic/doD.html
	}
	
	
	//List객체 데이터 출력시 if조건문 사용하기. 나이가 18세 이상이면 성년, 아니면 미성년
	@GetMapping("/doE")
	public String doE(Model model) {
		User user1 = new User("이몽룡", 20);
		User user2 = new User("이춘향", 15);
		
		List<User> list = new ArrayList<>(Arrays.asList(user1, user2));
		model.addAttribute("list", list);
		
		return "/basic/doE";   // 타임리프 파일명. /basic/doE.html
	}
	
	
	//쿼리스트링에 사용할 파라미터. 주소? 쿼리스트링
	//기본주소, 기본주소+쿼리스트링, 경로주소. 경로주소+쿼리스트링 예제
	//HomeController의 매핑주소를 사용하고 있음.
	@GetMapping("/doF")
	public String doF(Model model) {
		model.addAttribute("param1", "user01");
		model.addAttribute("param2", "1234");
		
		
		return "/basic/doF";	// 타임리프 파일명. /basic/doF.html
	}
	
	
	
	//리터럴(Literal) 예제. 데이터를 말한다.
	//즉, 문자열과 변수를 연결할 때 ->  |문자열${변수}|
	@GetMapping("/doG")
	public String doG(Model model) {
		model.addAttribute("data", "Spring!");
		
		return "/basic/doG";	// 타임리프 파일명. /basic/doG.html
	}
	
	
	//연산예제
	@GetMapping("/doH")
	public String doH(Model model) {
		model.addAttribute("nullData", null);
		model.addAttribute("data", "spring!");
		return "/basic/doH";	// 타임리프 파일명. /basic/doH.html
	}
	
}
