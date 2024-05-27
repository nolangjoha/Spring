package com.docmall.demo.service;

import com.docmall.demo.domain.UserInfoVO;

public interface UserInfoService {

	// [아이디 중복체크]
	String idCheck(String u_id);
	
	// [회원가입저장]
	void join(UserInfoVO vo);
	
	// [로그인작업] 로그인을 위한 아이디는 1개만 가져오니까 UserInfVO로 작업한다.
	UserInfoVO login(String u_id);
	
}
