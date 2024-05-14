package com.docmall.demo.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int statrPage;//각 블럭에서 출력할 시작페이지
	private int endPage; // 각 블럭에서 출력할 킅페이지
	private boolean prev, next;//다음, 이전 아이콘 표시여부// true: 활성화, flase:비활성화
	private int total; //테블 전체 데이터 개수/ ceil(무조건 올림) 등을 사용하여 종료 페이지 값을 구함.
	
	//Criteria 클래스 사용(미리 생성)
	private Criteria cri;
	
	
	public PageDTO() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
