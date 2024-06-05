package com.docmall.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor // 모든필드를 매개변수로 한 생성자
@Data  //getter, setter, equals, hashCode, toString, canEqual
public class User {

	private String username;
	private int age;
	
	
}
