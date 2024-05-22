package com.docmall.demo.service;

import org.springframework.stereotype.Service;

import com.docmall.demo.mapper.UserInfoMapper;

import lombok.RequiredArgsConstructor;

//bean생성 : userInfoServiceImpl(u자가 소문자로 바뀜)
@RequiredArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService  {

	private final UserInfoMapper userInfoMapper;
	
	/* 수동으로 의존성 주입해줄 때
	public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}
	*/
}
