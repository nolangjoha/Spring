package com.docmall.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.demo.domain.CategoryVO;
import com.docmall.demo.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryMapper categoryMapper;

	
	// [1차 카테고리]
	@Override
	public List<CategoryVO> categoryAllList() {
		
		return categoryMapper.categoryAllList();
	}


	@Override
	public List<CategoryVO> subCategoryList(Integer c_pcode) {
		
		return categoryMapper.subCategoryList(c_pcode);
	}
	
}
