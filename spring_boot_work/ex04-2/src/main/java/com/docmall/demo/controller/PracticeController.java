package com.docmall.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.PracticeVO;
import com.docmall.demo.service.PracticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/practice/")
@Controller
public class PracticeController {

	//의존성 주입
	@Autowired	
	private PracticeService practiceService;
	
	//글쓰기 폼
	@GetMapping("write")
	public void write() {
		log.info("write 부름");
	}
	
	@PostMapping("write")
	public String write(PracticeVO vo) {
		log.info("Controller write() post타입 실행");
		practiceService.write(vo);
		return "redirect:/practice/list";
	}
	
	
	
	@GetMapping("list")
	public void list(Model model) {
		
		List<PracticeVO>list = practiceService.list();
		model.addAttribute("list", list); // ("jsp에서 참조할 이름", 데이터 값)
		
			log.info("Controller.java //list불렀음");
	}
	
	
}
