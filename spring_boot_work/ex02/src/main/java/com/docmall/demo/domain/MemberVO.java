package com.docmall.demo.domain;

import java.util.Date;

public class MemberVO {

	private Long idx;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joindate;
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [idx=" + idx + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email
				+ ", joindate=" + joindate + "]";
	}
	
	
	
	
	
	
	
}
