package com.docmall.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.docmall.demo.dto.AttachFileDTO;
import com.docmall.demo.service.UploadService;
import com.docmall.demo.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/upload/*")
@RequiredArgsConstructor
@Controller
public class UploadController {

	private final UploadService uploadService;
	private FileUtils fileUtils;
	
	@Value("${org.docmall.upload.path}")//설정과 관계된 설정 들은 추후 수정을 위해서라도 외부에서 참조하는 것이 좋다.
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
			
			// new File(파일 또는 폴더경로)
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			//
			
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
	
	//ajax요청으로 파일업로드작업을 진행하고, 업로드된 파일목록정보를 리턴해주는 기능을 갖고 있다.
	//리턴값은<List<AttachFileDTO>  -> json으로 변환되어 클라이언트로 전송된다.
	//ResponseEntity는 감싸주는 역할이고 실질적으로 리턴타입은 List<AttachFileDTO>이다.
	@ResponseBody //ajax요청받는 매핑주소는 이 어노테이션을 달아야 한다.
	@PostMapping(value="uploadAjaxAction", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile){  
		ResponseEntity<List<AttachFileDTO>> entity = null; 
		
		//업로드한 파일정보 목록
		List<AttachFileDTO> list = new ArrayList<>();
		
		String uploadDateFolder = fileUtils.getDateFolder();
		
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachFileDTO = new AttachFileDTO();
			
			// 1) 클라이언트 파일이름
			String clientFileName = multipartFile.getOriginalFilename();
			attachFileDTO.setFileName(clientFileName);
			
			// 2) 실제 업로드한 파일명
			attachFileDTO.setUuid(fileUtils.uploadFile(uploadFolder, uploadDateFolder, multipartFile));
			
			// 3) 날짜 폴더명
			attachFileDTO.setUploadPath(uploadDateFolder);
			
			File saveFile = new File(uploadFolder, clientFileName);
			
			if(fileUtils.checkImageType(saveFile)) {
				
				// 4)이미지 파일여부 
				attachFileDTO.setImage(true);
			}
			list.add(attachFileDTO);
			}
		
		entity = new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
		return entity;
	}
	
	//<img src="매핑주소">
		@GetMapping("display")
		public ResponseEntity<byte[]> getFile(String fileName){
				
			ResponseEntity<byte[]> entity = null;
			
			try {
				entity = fileUtils.getFile(uploadFolder, fileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return entity;	
		}
		
		
		// [파일삮제]
		@PostMapping(value = "deleteFile")
		@ResponseBody
		public ResponseEntity<String> deleteFile(String dateFolderName, String fileName, String type){
			
			log.info("fileName: " + fileName);
			log.info("type: " + type);
			
			ResponseEntity<String> entity = null;
			
			fileUtils.delete(uploadFolder, fileName, type);
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			return entity;
		}
		
		
}

