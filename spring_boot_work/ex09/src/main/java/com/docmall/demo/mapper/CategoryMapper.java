package com.docmall.demo.mapper;

import java.util.List;

import com.docmall.demo.domain.CategoryVO;

public interface CategoryMapper {

	// [1차 카테고리]
	List<CategoryVO> categoryAllList();
	
	// [2차 카테고리]
	List<CategoryVO> subCategoryList(Integer c_pcode);
	
	
	
	
}
