package com.docmall.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.demo.domain.CategoryVO;
import com.docmall.demo.mapper.AdminProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AdminProductServiceImpl implements AdminProductService {

	private final AdminProductMapper adminProductMapper;

	//[1차 카테고리_생산품]
	@Override
	public List<CategoryVO> categoryAllList() {
		// TODO Auto-generated method stub
		return adminProductMapper.categoryAllList();
	}

	//[2차 카테고리]
	@Override
	public List<CategoryVO> subCategoryList(Integer c_pcode) {
		// TODO Auto-generated method stub
		return adminProductMapper.subCategoryList(c_pcode);
	}


}
