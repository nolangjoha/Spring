package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.docmall.demo.domain.MemberVO;

/*
 주소
 회원가입(get) /member/join
 회원저장(post) /member/join 
 회원목록(get) /member/list
 */

@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//회원가입 폼
	@GetMapping("join")
	public void join() {
		logger.info("회원가입");
	}
	
	//회원저장
	@PostMapping("join")	
	public String join_ok(MemberVO vo) {
		logger.info("회원가입정보:" + vo);
		return "redirect:/member/list";
	}
	
	//글목록
	@GetMapping("list")
	public void list() {
		logger.info("리스트");
	}

}
