package com.docmall.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.CategoryVO;
import com.docmall.demo.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/category/*")
@RequiredArgsConstructor
@Slf4j
@Controller
public class CategoryController {
	private final CategoryService categoryService;
	
	// [1차 카테고리]
	@GetMapping("catelist")
	public void categorList(CategoryVO vo, Model model) throws Exception{ //Model 인터페이스로 import
		
		/*
		List<CategoryVO> categoryAllList = categoryService.categoryAllList();
		model.addAttribute("catelist",categoryAllList);
		*/
		model.addAttribute("catelist",categoryService.categoryAllList());  //위의 두줄을 한줄로 할 수도 있다.
	}
	
	
	// [2차 카테고리] - catelist.jsp에서 ajax방식사용
	@GetMapping("subcatelist")
	public ResponseEntity<List<CategoryVO>> subCateList(Integer c_pcode) throws Exception{
		ResponseEntity<List<CategoryVO>> entity = null;
		
			log.info("1차 카테고리 코드: " + c_pcode);
			
			entity = new ResponseEntity<List<CategoryVO>>(categoryService.subCategoryList(c_pcode), HttpStatus.OK);
			
		return entity;
	}
	
	
	
	
	
	
}
