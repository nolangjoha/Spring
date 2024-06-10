package com.docmall.demo.dto;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	//페이지 필드
	private int pageNum; // 선택된 페이지 번호를 저장하기 위한 변수, 처음엔 1
	private int amount;  // 페이지마다 출력할 게시물 개수, 10을 넣으면 페이지당 10개씩 출력 
	
	//검색필드
	private String type;  	// 선택한 검색종류(옵션6가지) : 1.제목만(T), 2.글작성자(W), 3.내용(C) 4. 제목 or 작성자(TW) 5. 제목 or 내용(TC) 6. 제목 or 작성자 or 내용(TWC)
	private String keyword; // 검색어 저장되는 곳.
	
	public Criteria() {
		this(1, 10);
	}
	
	//매개변수가 있는 생성자
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		
		System.out.println("pageNum:"+ pageNum +", amount:" + amount);
	}
	
	public String[] getTypeArr() { // 필드에 typeArr는 없지만 return으로 값을 제어해줌...xml에서 사용하기 위해 get스타일의 메서드를 만든것이다.
		return type == null ? new String[] {} : type.split("");  // 클라이언트로부터 값을 받지 못했을때(null) > 공백상태가 되는것. 
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
				
		return builder.toUriString();
	}
	
	
	
}
