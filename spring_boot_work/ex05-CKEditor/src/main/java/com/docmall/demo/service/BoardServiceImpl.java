package com.docmall.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	//의존성 주입 :필드주입방식 -- mapper의 객체가 @Autowired으로 boardMapper에 자동으로 주입되었다.
				// 인터페이스는 객체 생성이 안되므로 mapper인터페이스를 구현한 프록시 클래스에서 객체생성이 되서 거기서 들어온것.
	@Autowired 
	private BoardMapper boardMapper;
	//BoardMapper.java의 메서드를 호출 할 수 있음.

	@Override
	public void write(BoardVO vo) {
		boardMapper.write(vo);
	}
/*
	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return boardMapper.list();
	}
*/
	@Override
	public BoardVO get(Long bno) {
		//조회수 증가
		boardMapper.readCount(bno);
		
		return boardMapper.get(bno);
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		boardMapper.modify(vo);
	}

	@Override
	public void delete(Long bno) {
		// TODO Auto-generated method stub
		boardMapper.delete(bno);
	}

	@Override
	public List<BoardVO> listWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.listWithPaging(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(cri);
	}
	
}
