package com.docmall.demo.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage;  //각 블럭에서 출력할 시작페이지 번호
	private int endPage;    // 각 블럭에서 출력할 끝 페이지 번호
	private boolean prev, next; //다음,이전 아이콘을 표시여부: true값이면 아이콘 활성화, false는 비활성화
	private int total; //테이블의 전체 데이터 개수, ceil(무조건 올림)등을 써서 종료페이지 값을 구한다.
	private Criteria cri; 
	
	

	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		int pageSize = 10; 
		int endPageInfo = pageSize - 1; //10 - 1 = 9. endPageInfo에 9값 대입
		
		this.endPage = (int) (Math.ceil(cri.getPageNum()/(double)pageSize))*pageSize; // 엔드페이지 구현 후 시작페이지를 구한다.
		
		this.startPage = this.endPage - endPageInfo;
		
		int realEnd = (int) (Math.ceil((total*1.0) / cri.getAmount()));  //괄호 잘보기 
		if(realEnd <= this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	
	
	
}
