package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.LoginDTO;

//게시판 기능에 사용하는 컨트롤러 클래스
/*
 주소
 글쓰기(get) /board/write
 글저장(post) /board/write 
 글목록(get) /board/list
 */

@RequestMapping("/board/*")
@Controller
public class SampleController2 {
	
	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);
	
	//글쓰기 폼
	@GetMapping("write")
	public void write() {
		logger.info("글쓰기");
	}
	
	//글쓰기 저장 - (규칙)DB의 테이블에 insert, update,delete 작업을 하는 매핑주소는 현재까지는 @PostMapping로 설정한다.
	//(규칙)메서드의 리턴타입은 String으로 해야한다. 
	@PostMapping("write")	
	public String write(BoardVO vo) {
		// String을 쓰는이유 : return에 redirect를 쓰기 위해서
		//사용자가 입력한 게시판 데이터를 BoardVO vo받고, 데이터베이스의 게시판테이블에 저장한다. 
		
		return "redirect:/board/list"; // redirect:가 빠지면 있으면 주소로 이동, 없으면 jsp파일을 말한다.
		// redirect를 쓸 때 ""를 써야한다.
	}
	
	//글목록
	@GetMapping("list")
	public void list() {
		logger.info("리스트");
	}

}
