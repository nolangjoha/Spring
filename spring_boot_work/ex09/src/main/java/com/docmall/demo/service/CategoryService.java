package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.CategoryVO;

public interface CategoryService {

	// [1차 카테고리]
	List<CategoryVO> categoryAllList();
	
	// [2차 카테고리]
	List<CategoryVO> subCategoryList(Integer c_pcode);
	
}
