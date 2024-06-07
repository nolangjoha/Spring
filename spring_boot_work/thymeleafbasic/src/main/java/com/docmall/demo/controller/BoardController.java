package com.docmall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.BoardVO;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Controller
@Slf4j
public class BoardController {

	@GetMapping("/write")
	public String write(Model model) {
		model.addAttribute("board", new BoardVO());
		return "/board/write";
	}
	
	@PostMapping("/write")
	public String write(@Valid BoardVO board, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/board/write";  //jsp혹은 타임리프 페이지(write.html)
		}
		
		log.info("called write");
		
		return "redirect:/board/list";
	}
	
	
}