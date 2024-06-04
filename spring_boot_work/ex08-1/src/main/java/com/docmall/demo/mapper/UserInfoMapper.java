package com.docmall.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.docmall.demo.domain.UserInfoVO;
import com.docmall.demo.dto.Criteria;

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
	
	// [마이페이지 수정하기]
	void modify(UserInfoVO vo);
	
	// [비밀전호 변경작업]
	// 파라미터타입이 2개면 파람작업을 해줘야 한다.
	void changePw(@Param("u_id") String u_id, @Param("new_pwd") String new_pwd);
	
	// [회원탈퇴]
	void delete(String u_pwd);
	
	// [아이디 찾기]
	String idfind(@Param("u_name") String u_name, @Param("u_email") String u_email);
	
	// [비밀번호 찾기]
	String pwfind(@Param("u_id") String u_id, @Param("u_name") String u_name, @Param("u_email") String u_email);
	
	// [비밀번호 업데이트]
	void tempPwUpdate(@Param("u_id") String u_id, @Param("u_pwd") String u_pwd);
	
	
	//[회원리스트] _ 출력만
	List<UserInfoVO> memList();
	
	//[회원리스트] _ 페이지 및 검색조건 추가
	List<UserInfoVO> memListWithPaging(Criteria cri);
	
	//회원 검색조건
	//검색조건이 추가된 총 데이터 개수
	int getTotalCount(Criteria cri);
	
	
}
