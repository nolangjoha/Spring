package com.docmall.demo.mapper;

import com.docmall.demo.domain.UserInfoVO;

//UserInfoMapper.xml mapper파일 
//인터페이스는 객체생성이 불가능하다.
//스프링에서는 Mapper 인터페이스를 구현한 프록시 클래스가 내부적으로 bean으로 자동생성되어 의존성주입을 해준다.
public interface UserInfoMapper {

	// [아이디 중복체크]
	String idCheck(String u_id);
	
	// [회원가입저장]
	void join(UserInfoVO vo);
	
	// [로그인작업] 로그인을 위한 아이디는 1개만 가져오니까 UserInfVO로 작업한다.
	UserInfoVO login(String u_id);
	
}
