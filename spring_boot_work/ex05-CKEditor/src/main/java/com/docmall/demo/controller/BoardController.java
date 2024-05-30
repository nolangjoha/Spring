package com.docmall.demo.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.dto.PageDTO;
import com.docmall.demo.service.BoardService;
import com.docmall.demo.util.FileUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j  //log객체 지원
@RequestMapping("/board/*")  //jsp(view)파일을 관리하기 위한 board포더 생성
@Controller
public class BoardController {

	//CKeditor파일 업로드 경로
	@Value("${file.ckdir}") // 환경설정에 있던 임의로 지정한 파일업로드 경로 이름, 이 이름을 통해 C:\\Dev\\upload\\ckeditor\\경로가 들어오게 되는 것.
	private String uploadCKpath;
	
	
	//의존성 주입 :필드주입방식
	@Autowired
	private BoardService boardService;
	//BoardServiceImpl.java의 메서드를 호출할 수 있음.
	
	//글쓰기 폼  // 제일 먼저 만든다 / DB작업이 필요 없이 폼만 만드는 거니까
	@GetMapping("write")
	public void write() {
		log.info("called. write");
	}
	
	//글쓰기 저장
	@PostMapping("write")
	public String write(BoardVO vo) {
		log.info("게시판 입력데이터:" + vo);
		
		//db저장.// 이 작업을 하려면 mapper와 service 순차적으로 작업을 해야한다.
		boardService.write(vo);
		
		return "redirect:/board/list";
	}
	
	// [CKeditor 업로드]
	// MultipartFile upload : CKEditor의 업로드 탭에서 나온 파일첨부 <input type="file" name="upload">을 참조함.
	//'upload'가 매우 중요함. 회사마다 다름. 위 방법으로 확인하고 일치시켜줘야함. 
	//HttpServletRequest request : 클라이언트의 요청정보를 가지고 있는 객체
	//HttpServletResponse response : 서버에서 클라이언트에게 보낼 정보를 응답하는 객체
	// < 1. 이미지를 서버에 전송>
	@PostMapping("/imageupload")
	public void imageupload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		
		//자바의 입출력 스트림을 사용할 것이다.
		//자바에 있는 파일을 브라우저에 출력해주는 것이 출력스트림 
		OutputStream out = null;
		PrintWriter printWriter = null; // 서버에서 클라이언트에게 응답정보를 보낼 때 사용.(업로드한 이미지정보를 브라우저에게 보내는 작업용도)
		
		try {
			// 1) CKeditor을 이용한 파일업로드 처리.
			String fileName = upload.getOriginalFilename(); // 업로드 할 클라이언트 파일이름
			byte[] bytes = upload.getBytes(); // 업로드할 파일의 바이트 배열
			
			//파일이 올라갈 경로와 올라간 파일 명을 합치는 작업 // uploadCKpath에는 파일경로가 들어있다.  
			// 환경설정에서 업로드 경로 마지막에 '\\'을 추가한 이유 : 파일명이 추가될 시 폴더와 구분해주기 위해, 안넣게 되면 마지막 폴더와 파일명이 합쳐져 에러발생
			String ckUploadPath = uploadCKpath + fileName; //  C:\\Dev\\upload\\ckeditor\\ + "abc.gif"
		
			out = new FileOutputStream(ckUploadPath); //  C:\\Dev\\upload\\ckeditor\\abc.gif 파일생성. 0byte (껍데기만 일단 생성) // 여기에 사용자가 입력한 바이트 배열을 채워넣어야 완벽한 파일이 된다.
			
			out.write(bytes); // 빨대(스트림)의 공간에 업로드한 파일의 바이트 배열을 채운상태다. 다음단계에서는 이 내용물을 쏴줘야 한다.

			out.flush();//  빨대로 가져온 내용물을 채워줫다 C:\\Dev\\upload\\ckeditor\\abc.gif  0byte ->크기가 정상적인 파일로 인식
			
			
			// 2) 이미지 파일에 대한 정보를 클라이언트에게 보내는 작업 
			
			
			printWriter = response.getWriter();
			
			String fileUrl = "/board/display/" + fileName;    //매핑주소/이미지파일명
			//String fileUrl = fileName;
			
			
			
			//CKEditor 4.12에서는 파일정보를 다음과 같이 구성하여 전송해야한다.
			//{"filename" : "abc.gif", "uploaded":1, "url":"/uploaded/abc.gif"}  // 자바스크립트 json문법 구조 스타일
			//{"filename" : 변수, "uploaded":1, "url":변수}
			//파일명과 경로는 변수로 처리해줄수 있으나 나머지는 문자열로되어 있으므로 문자열끼리 이어주는 작업을 해줘야 한다. 
			printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
   				     		  // {"filename" : 를 복붙했는데 ""관련 오류나니까 알아서 \룰 넣어줫다.  \를 붙이면 \다음에 온 "는 데이터로 인식한다.
			printWriter.flush();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(out != null) {
				try{
					out.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(printWriter != null) printWriter.close();
		}
		
	}
	
	
	
	// < 2. 전송된 이미지를 다시 에디터에 출력될 수 있도록 하는 작업>
	//<img src="매핑주소">
	@GetMapping("/display/{fileName}")
	public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName){
			
		log.info("파일이미지 : "+ fileName);
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			entity = FileUtils.getFile(uploadCKpath, fileName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return entity;	
	}
	
	
	
	//글목록
	/*
	@GetMapping("list")
	public void list(Model model) {
		
		//데이터소스(list)를 jsp에서 사용할 경우에는 파라미터를 Model을 사용한다.
		List<BoardVO> list = boardService.list(); 
		model.addAttribute("list",list); 
		
		logger.info("리스트");
	}
	*/
	
	//메서드의 파라미터로 Criteria cri를 사용한 이뉴?
	//클라이언트로부터 pageNum, amout, type, keyword를 받아와 사용하기 위해서다.
	//처음엔 클라이언트로부터 받은 필드값이 없다. 처음엔 기본생성자가 호출되어 필드값에 사용된다.
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		//데이터소스(list)를 jsp에서 사용할 경우에는 파라미터를 Model을 사용한다.
		
		// list에 10개씩 데이터가 들어와 있다.
		List<BoardVO> list = boardService.listWithPaging(cri);
		//cir를 넣은 이유 mapper.xml까지 정보를 보내주기 위해
		
		log.info("게시물 목록 데이터:" + list);
		
		//1)목록으로 뿌릴 데이터 10건 (model 1)
		model.addAttribute("list", list);  
						// "list"(jsp명)와 list는 꼭 같은 이름이 아니여도 된다.
						//"list"를  list.jsp에 가서 이엘문법으로 list에 있는 걸 끌어와 사용한다.
		
		int total = boardService.getTotalCount(cri);
		PageDTO pageDTO = new PageDTO(cri, total);
		
		log.info("페이징 기능데이터:" + pageDTO);
		
		//2) 페이징 기능 1  2  3  ...[next] (model 2)
		model.addAttribute("pageMaker", pageDTO);
		//페이징 기능을 만들기위한 것이 pageDTO에서 들어있고 "pageMaker"이름으로 jsp에서 사용한다. 
	}
	
	
	//게시물 조회, 게시물수정
	// 파라미터로 사용하고 있는 Criteria cri정보를 jsp에서 참조하고싶은 경우 @ModelAttribute("cri")를 사용.
	@GetMapping(value= {"get","modify"})
	public void get(Long bno, @ModelAttribute("cri") Criteria cri, Model model) { 
		//Criteria cri넣은 이유 : 게시물을 보다가 수정, 삭제, 리스트 등을 누를때 원래 보고있었던 목록에서 사용됐던 검색과 페이지 정보를 가지고 있어야 하는데 그게 Criteria cri가 가지고 있다. 
		//@ModelAttribute를 사용한 이유 : jsp에서 사용하기 위해서
		
		log.info("게시물번호:"+ bno);
		
		//게시물 정보 읽어오기(조회수증가 작업 포함)
		BoardVO boardVO = boardService.get(bno);
		//조회수증가를 여기에 쓰면 안됨. 위 작업 전에 작성하기
		
		model.addAttribute("boardVO", boardVO);
	}
	
	//게시물 수정하기 
	@PostMapping("modify")
	public String modify(BoardVO vo, Criteria cri) {
		log.info("수정데이터: "+ vo);
		boardService.modify(vo);
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	//게시물 삭제하기 //write, modify, insert, update, delete의 데이터 타입은 String// return값 사용을 위해(다른곳으로 이동)
	@GetMapping("delete")
	public String delete(Long bno, Criteria cri/*, RedirectAttributes rttr*/) {
		log.info("삭제 글번호 :" + bno);
		boardService.delete(bno);
		/*
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		 */
		//redirect:/board/list?pageNum=2&amount=10&type=T&keyword=사과
		//return "redirect:/board/list";
		
		return "redirect:/board/list" + cri.getListLink();
		//RedirectAttributes대신 criteria에 getListLink메서드를 만들어 사용할 수 있다.
	}
	
	
	
	
} 
