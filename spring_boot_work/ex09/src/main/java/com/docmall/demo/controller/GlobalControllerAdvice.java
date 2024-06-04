package com.docmall.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.docmall.demo.domain.CategoryVO;
import com.docmall.demo.service.AdminProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 
 com.docmall.demo.controller 패키지에서 필요한 1차 카테고리  model작업을
 아래 파일에서 1번만 작업하변, 여러군데에서 사용 가능함.
 @ModelAttribute 반드시 달아줘야 함. 
 
  */

@RequiredArgsConstructor
@ControllerAdvice(basePackages = {"com.docmall.demo.controller"}) //패키지 주소 넣기 // 배열로 되어 있으므로 주소를 계속 넣을 수 있음.
@Slf4j
// @Controller 사용하지 않음. @ControllerAdvice 사용
public class GlobalControllerAdvice {

	private final AdminProductService adminProductService;
	
	
	@ModelAttribute
	public void categoryList(Model model) throws Exception {
		
		log.info("GlobalControllerAdvice");
		
		//1차 카테고리
		model.addAttribute("catelist",adminProductService.categoryAllList());  
	}
	
	
}
