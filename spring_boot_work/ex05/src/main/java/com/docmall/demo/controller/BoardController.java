package com.docmall.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.dto.PageDTO;
import com.docmall.demo.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j  //log객체 지원
@RequestMapping("/board/*")  //jsp(view)파일을 관리하기 위한 board포더 생성
@Controller
public class BoardController {

	//로그객체
	//private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//의존성 주입 :필드주입방식
	@Autowired
	private BoardService boardService;
	//BoardServiceImpl.java의 메서드를 호출할 수 있음.
	
	//글쓰기 폼  // 제일 먼저 만든다 / DB작업이 필요 없이 폼만 만드는 거니까
	@GetMapping("write")
	public void write() {
		log.info("called. write");
	}
	
	//글쓰기 저장
	@PostMapping("write")
	public String write(BoardVO vo) {
		log.info("게시판 입력데이터:" + vo);
		
		//db저장.// 이 작업을 하려면 mapper와 service 순차적으로 작업을 해야한다.
		boardService.write(vo);
		
		return "redirect:/board/list";
	}
	
	//글목록
	/*
	@GetMapping("list")
	public void list(Model model) {
		
		//데이터소스(list)를 jsp에서 사용할 경우에는 파라미터를 Model을 사용한다.
		List<BoardVO> list = boardService.list(); 
		model.addAttribute("list",list); 
		
		logger.info("리스트");
	}
	*/
	
	//메서드의 파라미터로 Criteria cri를 사용한 이뉴?
	//클라이언트로부터 pageNum, amout, type, keyword를 받아와 사용하기 위해서다.
	//처음엔 클라이언트로부터 받은 필드값이 없다. 처음엔 기본생성자가 호출되어 필드값에 사용된다.
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		//데이터소스(list)를 jsp에서 사용할 경우에는 파라미터를 Model을 사용한다.
		
		// list에 10개씩 데이터가 들어와 있다.
		List<BoardVO> list = boardService.listWithPaging(cri);
		//cir를 넣은 이유 mapper.xml까지 정보를 보내주기 위해
		
		log.info("게시물 목록 데이터:" + list);
		
		//1)목록으로 뿌릴 데이터 10건 (model 1)
		model.addAttribute("list", list);  
						// "list"(jsp명)와 list는 꼭 같은 이름이 아니여도 된다.
						//"list"를  list.jsp에 가서 이엘문법으로 list에 있는 걸 끌어와 사용한다.
		
		int total = boardService.getTotalCount(cri);
		PageDTO pageDTO = new PageDTO(cri, total);
		
		log.info("페이징 기능데이터:" + pageDTO);
		
		//2) 페이징 기능 1  2  3  ...[next] (model 2)
		model.addAttribute("pageMaker", pageDTO);
		//페이징 기능을 만들기위한 것이 pageDTO에서 들어있고 "pageMaker"이름으로 jsp에서 사용한다. 
	}
	
	
	//게시물 조회, 게시물수정
	@GetMapping(value= {"get","modify"})
	public void get(Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		
		log.info("게시물번호:"+ bno);
		
		//게시물 정보 읽어오기(조회수증가 작업 포함)
		BoardVO boardVO = boardService.get(bno);
		//조회수증가를 여기에 쓰면 안됨. 위 작업 전에 작성하기
		
		model.addAttribute("boardVO", boardVO);
	}
	
	//게시물 수정하기 
	@PostMapping("modify")
	public String modify(BoardVO vo, Criteria cri) {
		log.info("수정데이터: "+ vo);
		boardService.modify(vo);
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	//게시물 삭제하기 //write, modify, insert, update, delete의 데이터 타입은 String// return값 사용을 위해(다른곳으로 이동)
	@GetMapping("delete")
	public String delete(Long bno, Criteria cri/*, RedirectAttributes rttr*/) {
		log.info("삭제 글번호 :" + bno);
		boardService.delete(bno);
		/*
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		 */
		//redirect:/board/list?pageNum=2&amount=10&type=T&keyword=사과
		//return "redirect:/board/list";
		
		return "redirect:/board/list" + cri.getListLink();
		//RedirectAttributes대신 criteria에 getListLink메서드를 만들어 사용할 수 있다.
	}
	
	
	
	
} 
