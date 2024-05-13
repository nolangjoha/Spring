package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.ReplyVO;
import com.docmall.demo.dto.Criteria;

public interface ReplyService {

	List<ReplyVO> getListPaging(Criteria cri, Long bno);
	
	int getCountByBno(Long bno);
	
	void insert(ReplyVO vo);
	
	void update(ReplyVO vo);
}
