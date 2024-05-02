package com.docmall.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	//의존성 주입
	@Autowired 
	private BoardMapper boardMapper;
	//BoardMapper.java의 메서드를 호출 할 수 있음.

	@Override
	public void write(BoardVO vo) {
		boardMapper.write(vo);
		
	}
	
}
