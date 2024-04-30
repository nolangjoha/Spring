package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/* 
1)다른 매핑주소로 이동하는 방법
2)RedirectAttributes 인터페이스 사용하기
   2-1) attribute()메서드 
   2-2) addFlashAttribute()메서드
*/
@Controller
public class SampleController5 {

	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(SampleController5.class); 
	
	// 클라이언트 /doL 주소 요청하고, 메서드가 실행 된 후 jsp가 동작하는 것이 아니라 다른 매핑주소(/doM)로 이동하는 작업
	// 위와 같은 작업 시 1) 메서드의 리턴타입은 String 2) 리턴값은 “redirect:/매핑주소”형식으로 작성해야 한다.

	@RequestMapping("/doL") //
	public String doL(RedirectAttributes rttr) {
		logger.info("called doL...");
		
		rttr.addAttribute("title", "spring study");
		rttr.addAttribute("idx", 10);
		
		//정상작동하게 하려면
		//1) 수동으로 리턴값에  아래 주소 넣기
		// /doM?title=spring study&idx=10
		//2) 19행 괄호에 매개변수를 넣고 22,23행 처럼 변수값을 설정해주면 31번 행과 동일한 의미이다. 
		
		//return "redirect:/doM?title=spring study&idx=10"; // jsp파일명으로 해석하지 않는다.

		return "redirect:/doM"; 
		// 결과 http://localhost:9090/doM?title=spring+study&idx=10		
	}
	
	@RequestMapping("/doM")  //매핑주소가 jsp파일명이 된다.
	public void doM(String title, int idx) {
		
		logger.info("리디렉트 요청으로 실행됨.");
		logger.info("title : " + title);
		logger.info("idx:" + idx);
	}
	/*******************************************************************************/
	
	@RequestMapping("/doN")
	public String doN(RedirectAttributes rttr) {

		//이렇게 사용하면 daO에서 아래 정보를 사용 할 수 있다. 위쪽 예제에서는 jsp에서 변수값 사용이 불가하였음.
		rttr.addFlashAttribute("msg", "ok");
		
		return "redirect:/doO";   //redirect를 사용시 jsp 파일명이 동작되지 않는다.
	}

	@RequestMapping("/doO")  //daO.jsp파일에서 msg키 이름 사용 가능
	public void doO() {
		
	}
	
}
