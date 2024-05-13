package com.docmall.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.demo.domain.ReplyVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  //final 필드를 이용하여 생성자를 생성한다. 그리고 그 생성자가 주입받는다.
@Service
public class ReplyServiceImpl implements ReplyService {

	/*롬복 @RequiredArgsConstructor 어노테이션을 클래스수준에 사용하면, 아래와 같은 매개변수를 가진 생성자가 자동 생성된다.(생성자주입)
	@Autowired 
	public ReplyServiceImpl(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}
	 */	
	
	private final ReplyMapper replyMapper;

	
	@Override
	public List<ReplyVO> getListPaging(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		return replyMapper.getListPaging(cri, bno);
	}


	@Override
	public int getCountByBno(Long bno) {
		// TODO Auto-generated method stub
		return replyMapper.getCountByBno(bno);
	}


	@Override
	public void insert(ReplyVO vo) {
		// TODO Auto-generated method stub
		replyMapper.insert(vo);
	}


	@Override
	public void update(ReplyVO vo) {
		// TODO Auto-generated method stub
		replyMapper.update(vo);
	}
	
	
	
}
