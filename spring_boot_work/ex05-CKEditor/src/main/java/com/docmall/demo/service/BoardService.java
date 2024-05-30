package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;

public interface BoardService {

	//글쓰기 저장
	void write (BoardVO vo);	
	
	//글목록
	//List<BoardVO> list();
	List<BoardVO> listWithPaging(Criteria cri);
	//list의 데이터 하나하나가 BoardVO의 구성을 사용한 데이터로 되어있다.
	
	
	//총 데이터 개수
	int getTotalCount(Criteria cri);
	
	//게시물 조회
	BoardVO get(Long bno);	
	
	//글 수정하기
	void modify(BoardVO vo);
	
	//글 삭제하기
	void delete(Long bno);
	
}
