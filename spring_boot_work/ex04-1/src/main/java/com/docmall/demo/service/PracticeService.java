package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.PracticeVO;

public interface PracticeService {

	//글쓰기 저장
	void write(PracticeVO vo);
	
	//글목록
	List<PracticeVO> list();
	
}
