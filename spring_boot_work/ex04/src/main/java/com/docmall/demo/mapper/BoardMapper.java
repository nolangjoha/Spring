package com.docmall.demo.mapper;

import java.util.List;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;

public interface BoardMapper {

	//글쓰기 저장
	void write (BoardVO vo);
	
	// 글목록
	List<BoardVO> list();
	List<BoardVO> listWithPaging(Criteria cri);
	
	//총 데이터 개수
	int getTotalCount();	
	
	//게시물 조회
	BoardVO get(Long bno);
	
	//조회수 증가
	void readCount(Long bno);
	
	//글 수정하기  
	void modify(BoardVO vo);
	
	//글 삭제하기
	void delete(Long bno);
	
	
}
