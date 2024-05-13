package com.docmall.demo.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-- rno, bno, retext, replyer, replydate, updatedate
@Getter
@Setter
@ToString
public class ReplyVO {

	private Integer rno;  //int가 제대로 안들어 올 것을 대비하여 Integer 클래스 타입으로 잡았다.
	private Long bno;
	private String retext;
	private String replyer;
	private Date replydate;
	private Date updatedate;
	
	
}
