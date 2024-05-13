package com.docmall.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.demo.domain.ReplyVO;
import com.docmall.demo.dto.Criteria;

public interface ReplyMapper {

	//mapper인터페이스의 메서드 파라미터가 2개 이상일 경우 반드시 @Param 어노테이션을 사용해야 한다.
	// Criteria cri에 들어왔던 정보가 "cri"로 들어와서 mapper.xml에서 사용 된다. ""내 다른 이름으로 해도 상관 없다. 단, xml에서는 ""이름을 사용한다.
	//Criteria - pageNum, amount (type, keyword : 이건 사용 안할거다)
	List<ReplyVO> getListPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
				
	int getCountByBno(Long bno);
	
	void insert(ReplyVO vo); 
	
	void update(ReplyVO vo);
	
}

