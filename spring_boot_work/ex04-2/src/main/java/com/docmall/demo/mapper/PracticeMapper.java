package com.docmall.demo.mapper;

import java.util.List;

import com.docmall.demo.domain.PracticeVO;

public interface PracticeMapper {

	//글쓰기 저장
	void write(PracticeVO vo);
	
	//글목록
	List<PracticeVO> list();
	

	
	
}
