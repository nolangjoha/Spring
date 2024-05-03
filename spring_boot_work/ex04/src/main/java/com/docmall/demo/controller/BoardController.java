package com.docmall.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.service.BoardService;

@RequestMapping("/board/*")
@Controller
public class BoardController {

	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//의존성 주입
	@Autowired
	private BoardService boardService;
	//BoardServiceImpl.java의 메서드를 호출할 수 있음.
	
	//글쓰기 폼  // 제일 먼저 만든다 / DB작업이 필요 없이 폼만 만드는 거니까
	@GetMapping("write")
	public void write() {
		logger.info("called. write");
	}
	
	//글쓰기 저장
	@PostMapping("write")
	public String write(BoardVO vo) {
		logger.info("게시판 입력데이터:" + vo);
		
		//db저장.// 이 작업을 하려면 mapper와 service 순차적으로 작업을 해야한다.
		boardService.write(vo);
		
		return "redirect:/board/list";
	}
	
	//글목록
	@GetMapping("list")
	public void list(Model model) {
		
		//데이터소스(list)를 jsp에서 사용할 경우에는 파라미터를 Model을 사용한다.
		List<BoardVO> list = boardService.list(); 
		model.addAttribute("list",list); 
		
		logger.info("리스트");
	}
	
	//게시물 조회, 게시물수정
	@GetMapping(value= {"get","modify"})
	public void get(Long bno, Model model) {
		
		logger.info("게시물번호:"+ bno);
		
		//게시물 정보 읽어오기(조회수증가 작업 포함)
		BoardVO boardVO = boardService.get(bno);
		//조회수증가를 여기에 쓰면 안됨. 위 작업 전에 작성하기
		
		model.addAttribute("boardVO", boardVO);
	}
	
	//게시물 수정하기 
	@PostMapping("modify")
	public String modify(BoardVO vo) {
		logger.info("수정데이터: "+ vo);
		boardService.modify(vo);
		
		return "redirect:/board/list";
	}
	
	//게시물 삭제하기 //write, modify, insert, update, delete의 데이터 타입은 String// return값 사용을 위해(다른곳으로 이동)
	@GetMapping("delete")
	public String delete(Long bno) {
		logger.info("삭제 글번호 :" + bno);
		boardService.delete(bno);
		return "redirect:/board/list";
	}
	
	
	
	
} 
