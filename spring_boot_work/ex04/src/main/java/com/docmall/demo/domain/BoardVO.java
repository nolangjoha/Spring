package com.docmall.demo.domain;

import java.util.Date;

//오라클 테이블의 컬럼을 소문자로 만들었다.
//bno, title, content, writer, regdate, updatedate, viewcount

public class BoardVO {

	private Long bno;  //참조타입 Long 클래스(랩퍼클래스)
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	private int viewcount;
	
	public Long getBno() {
		return bno;
	}
	public void setBno(Long bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", updatedate=" + updatedate + ", viewcount=" + viewcount + "]";
	}
	
	
	
	
}
