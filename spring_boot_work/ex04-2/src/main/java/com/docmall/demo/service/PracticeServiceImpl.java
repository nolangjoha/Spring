package com.docmall.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docmall.demo.domain.PracticeVO;
import com.docmall.demo.mapper.PracticeMapper;

@Service
public class PracticeServiceImpl implements PracticeService {

	@Autowired
	private PracticeMapper practiceMapper;
	
	@Override
	public void write(PracticeVO vo) {
		practiceMapper.write(vo);
	}

	@Override
	public List<PracticeVO> list() {
		return practiceMapper.list();
	}

}
