package com.docmall.demo.dto;

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
	private String type;  	// 선택한 검색종류(옵션): 제목만, 글작성자, 댓글내용, 댓글작성자 등
	private String keyword; // 검색어 저장되는 곳.
	
	//생성자 : 1페이지부터 보여주기 위해 해당 생성자를 생성했다.
	public Criteria() {
		this(1, 10);
	}
	
	//매개변수가 있는 생성자
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		System.out.println("pageNum:"+ pageNum +", amount:" + amount);
	}
	
	
	
}
