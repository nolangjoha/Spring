package com.docmall.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.demo.domain.ReplyVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.dto.PageDTO;
import com.docmall.demo.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//댓글기능과 관련된 매핑주소를 관리하는 클래스
//게시판의 get.jsp에서 작업에 필요한 내용
//RESP API 개발방법


@Slf4j  	// 로그객체 지원
@RequiredArgsConstructor
@RestController     // 이 어노테이션이 들어가면 웹페이지가 필요 없다. 
@RequestMapping("/replies/*") //이건 @RestController로인헤 폴더를 만드는게 아니라 이걸 사용한 댓글들을 관리하기 위해 만들었다.
public class ReplyController {

	private final ReplyService replyService;
	
	//요청주소. 
	// 1) /replies/pages/511/1  (REST API기술이론에서 권장하는 주소형태, 기존보다 더 심플하다는 주장, @RestController 과 연관있음.)  
		//   /replies/pages/100/1  주소(경로)에 존재하는 값을 사용할 때 구분되는 위치에 {이름}를 사용.
		// 만약 bno자리에 100이란 값이 들어왔으면 {bno}로 받고 "bno"로 받아서 Long bno변수에 들어가는 것이다.
	// 2) /replies?pages=1&bno=511   (전통적 주소타입)
	
	
	//produces의 역할? :  
	
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String, Object>> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page){
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		//1) 댓글목록 작업
		Criteria cri = new Criteria(page, 5);  
		List<ReplyVO> list = replyService.getListPaging(cri, bno);
		map.put("list", list);
		
		//2) 페이징 작업
		int total = replyService.getCountByBno(bno);
		PageDTO pageDTO = new PageDTO(cri, total);
		map.put("pageMaker", pageDTO);
		
		
		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		//ResponseEntity의 역할 : 데이터를 json형태로 보낸다. 클라이언트에게 보내는 데이터의 성격을 확실하게 함.
		
		return entity;
	}
	
	//댓글저장
	// 실제 반환타입은<String>
	//  consumes = "application/json": 클라이언트에서 보내는 데이터는 json이어야 한다. 
	//  produces = {MediaType.TEXT_PLAIN_VALUE} : 서버에서 클라이언트로 보내는 응답데이터는 text이다.
	// @RequestBody : json데이터를 ReplyVO vo로 변환해주는 기능(jackson-databind-2.15.4.jar라이브러리가 실제 json작업을 함.
	// 해당 기능을 사용하려면 pom.xml에 <artifactId>spring-boot-starter-web</artifactId>를 추가해야함.
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){ 
		ResponseEntity<String> entity = null;
		
		log.info("댓글데이터 : " + vo);
		
		replyService.insert(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	//댓글수정. put or patch
	@PutMapping(value="/modify", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo){ 
		ResponseEntity<String> entity = null;
		
		log.info("댓글수정데이터 : " + vo);
		
		//댓글수정작업
		replyService.update(vo);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	//댓글삭제. delete		일반적주소:/delete?rno=댓글번호  // rest api 경로형태 주소 : /delete/500
	@DeleteMapping(value="/delete/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE} ) // {MediaType.TEXT_PLAIN_VALUE} 작동되면 결과값을 text로 주겠다.
	public ResponseEntity<String> delete(@PathVariable("rno") Integer rno){
		ResponseEntity<String> entity = null;
		
		//댓글삭제작업
		replyService.delete(rno);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
				
		return entity;
	}
	
	
}
