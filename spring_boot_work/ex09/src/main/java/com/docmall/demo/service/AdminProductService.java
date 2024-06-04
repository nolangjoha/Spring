package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.CategoryVO;

public interface AdminProductService {

	// [1차 카테고리_생산품]
	List<CategoryVO> categoryAllList();
	
	// [2차 카테고리]
	List<CategoryVO> subCategoryList(Integer c_pcode);
	
}
