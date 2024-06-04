package com.docmall.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.CategoryVO;
import com.docmall.demo.service.AdminProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//상품관리

@RequiredArgsConstructor
@RequestMapping("/admin/product/*")
@Slf4j
@Controller
public class AdminProductController {

	
	private final AdminProductService adminProductService;
	
	//상품등록 카테고리
	@GetMapping("/pro_ins")
	public void pro_ins(Model model, CategoryVO vo) {
		
		//GlobalControllerAdvice.java 에서 1차 카테고리  model작업으로 필요가 없음.
		//model.addAttribute("catelist",adminProductService.categoryAllList());  //1차 카테고리
	}
	
	
	// [2차 카테고리]
	@GetMapping("/subcatelist")
	public ResponseEntity<List<CategoryVO>> subCateList(Integer c_pcode) throws Exception{
		ResponseEntity<List<CategoryVO>> entity = null;
		
			entity = new ResponseEntity<List<CategoryVO>>(adminProductService.subCategoryList(c_pcode), HttpStatus.OK);
			
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
}
