package com.docmall.demo.service;



import com.docmall.demo.domain.UserInfoVO;

public interface UserInfoService {

	// [아이디 중복체크]
	String idCheck(String u_id);
	
	// [회원가입저장]
	void join(UserInfoVO vo);
	
	// [로그인작업] 로그인을 위한 아이디는 1개만 가져오니까 UserInfVO로 작업한다.
	UserInfoVO login(String u_id);
	
	// [마이페이지 수정하기]
	void modify(UserInfoVO vo);
	
	// [비밀전호 변경작업]
	void changePw(String u_id, String new_pwd);
	
	// [회원탈퇴]
	void delete(String u_pwd);
	
	// [아이디 찾기]
		String idfind(String u_name, String u_email);
		
	// [비밀번호 찾기]
	String pwfind(String u_id,String u_name,String u_email);	
		
	// [비밀번호 업데이트]
	void tempPwUpdate(String u_id, String u_pwd);
	
	
}
