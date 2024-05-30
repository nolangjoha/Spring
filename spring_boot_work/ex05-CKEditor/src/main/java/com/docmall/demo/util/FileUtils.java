package com.docmall.demo.util;


import java.io.File;

import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;




//여러 Controller에서 사용될수 있는 기능이므로 클래스로 빼버렸음.(재사용성)

@Component  // 스프링에서 클래스를 자동관리. bean으로 관리 할 수 있게 해준다. 
public class FileUtils {

	// [기능4]이미지파일을 웹브라우저 화면에 보이는 작업.
	// <img src="abc.gif"> <img src="매핑주소"> 매핑주소를 통한 서버측에서 받아오는 바이트배열을 이용하여 브라우저가 이미지를 표시한다.
	/*
	  - String uploadPath : 서버 업로드폴더  예)"C:\\Dev\\upload\\pds"
	  - String fileName : 이미지 파일명 (날짜폴더명 포함)
	 */
	
	// 파일업로드되는 폴더가 외부 프로젝틍 존재하여, 보안적인 이슈가 있으므로, 업로드 파일들을 byte배열로 읽어서 클라이언트로 보낸다.
	public static ResponseEntity<byte[]> getFile(String uploadPath, String fileName) throws Exception{
		ResponseEntity<byte[]> entity = null;
		File file = new File(uploadPath, fileName);  // 이 경로는 실제 파일이 존재 해야 함.
		
		if(!file.exists()) { //파일이 존재하지 않으면 entity를 반환한다.
			return entity;
		}
		
		
		HttpHeaders headers = new HttpHeaders();
		//Files.probeContentType(file.toPath()) : MIME TYPE정보 예)image/jpeg
		headers.add("Content-Type", Files.probeContentType(file.toPath()));
		
		entity= new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		
		return entity;
	}
	
	
	
}
