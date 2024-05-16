package com.docmall.demo.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.docmall.demo.service.UploadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/upload/*")
@RequiredArgsConstructor
@Controller
public class UploadController {

	private final UploadService uploadService;
	
	@Value("${org.docmall.upload.path}")
	private String uploadFolder;
	
	// [1.업로드 방식] <form>태그를 이용한 방식.
	@GetMapping("uploadForm") //jsp파일로 접속했을 때 보이는 페이지
	public void uploadForm() {
	}	
	
	//com.docmall.demo.config 패키지 MultipartConfig.java클래스 안에 multipartResolver bean이 업로드파일을 참조하여
	// MultipartFile[] uploadFile 이 파라미터로 사용하게 해준다. 
	@PostMapping("uploadFormAction")  // 폼태그로 파일을 전송했을 때 보이는 페이지
	public void uploadFomPost(MultipartFile[] uploadFile) {
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------------------");
			log.info("파일이름: " + multipartFile.getOriginalFilename());
			log.info("파일크기: " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	// [2.업로드 방식] ajax를 이용한 방식.
	@GetMapping("uploadAjax")
	public void uploadAjax() {
		
	}
	
	
	
	
	
	
	
}
