package com.docmall.demo.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.docmall.demo.domain.UserInfoVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.mapper.UserInfoMapper;

import lombok.RequiredArgsConstructor;

//bean생성 : userInfoServiceImpl(u자가 소문자로 바뀜)
@RequiredArgsConstructor
@Service   // 중간계층의 비지니스로직을 목적하는 클래스 userInfoServiceImpl bean 자동생성
public class UserInfoServiceImpl implements UserInfoService  {

	private final UserInfoMapper userInfoMapper;
	// 롬복에서 제공하는 @RequiredArgsConstructor을 사용하지 않으면, 아래 생성자 구문을 수동으로 작성해야 한다.ㄴ
	/* 수동으로 의존성 주입해줄 때
	public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}
	*/
	
	private final PasswordEncoder passwordEncoder;
	
	// [아이디 중복체크]
	@Override
	public String idCheck(String u_id) {
		return userInfoMapper.idCheck(u_id);
	}
	
	// [회원가입저장]
	@Override
	public void join(UserInfoVO vo) {
		vo.setU_pwd(passwordEncoder.encode(vo.getU_pwd()));
		
		userInfoMapper.join(vo);
	}

	// [로그인작업] 로그인을 위한 아이디는 1개만 가져오니까 UserInfVO로 작업한다.
	@Override
	public UserInfoVO login(String u_id) {
		
		return userInfoMapper.login(u_id);
	}

	
	// [마이페이지 수정하기]
	@Override
	public void modify(UserInfoVO vo) {
		userInfoMapper.modify(vo);
		
	}

	// [비밀번호 변경하기]
	@Override
	public void changePw(String u_id, String new_pwd) {
		userInfoMapper.changePw(u_id, new_pwd);		
	}

	// [회원 탈퇴하기]
	@Override
	public void delete(String u_id) {
		userInfoMapper.delete(u_id);		
	}

	
	// [아이디 찾기]
	@Override
	public String idfind(String u_name, String u_email) {
		return userInfoMapper.idfind(u_name, u_email);
	}

	//[비밀번호 찾기]
	@Override
	public String pwfind(String u_id, String u_name, String u_email) {
		return userInfoMapper.pwfind(u_id, u_name, u_email);
	}

	// [비밀번호 업데이트]
	@Override
	public void tempPwUpdate(String u_id, String u_pwd) {
		userInfoMapper.tempPwUpdate(u_id, u_pwd);
	}

	//[회원리스트 표기만]
	@Override
	public List<UserInfoVO> memList() {
		// TODO Auto-generated method stub
		return userInfoMapper.memList();
	}

	//[회원리스트] _ 검색조건 및 페이징 
	@Override
	public List<UserInfoVO> memListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return userInfoMapper.memListWithPaging(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return userInfoMapper.getTotalCount(cri);
	}

	
	
	
	
	
}
