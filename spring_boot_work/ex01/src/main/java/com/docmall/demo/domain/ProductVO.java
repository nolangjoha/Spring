package com.docmall.demo.domain;

//오라클 데이터 베이스에 Product 테이블을 생성하고 구조를 정의한 클래스
public class ProductVO {

	// 실제 개발할때 모두 private 만 쓴다. 다른건 안씀.
	private String name;   //상품이름
	private double price;  // 상품가격
	
	//원래는 안만듬. 지금은 데이터베이스 없어서
	public ProductVO(String name, double price) {
//		super();     이건 컴파일 과정에서 알아서 생성됨.
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + "]";
	}

	
	
	
}

