package com.docmall.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	
	private Integer idx;
	
	@NotBlank(message = "제목은 필수 항목입니다.")
	@Size(max = 200)
	private String title;
	
	@NotBlank(message = "저자는 필수 항목 입니다.")
	@Size(max = 30)
	private String author;
	
	@NotBlank(message = "내용은 필수 항목입니다.")
	private String content;
	
	
}