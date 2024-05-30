package com.docmall.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.docmall.demo.domain.UserInfoVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

//용도? 인터셉터 기능을 하기위한 클래스 : Controller로 가는 신호를 가로챔.
// 어떤 매핑주소를 인터셉터 하는가?

@Slf4j
@Component 
public class LoginInterceptor implements HandlerInterceptor{
	
	// 3개의 메서드가 이벤트 특징을 갖고있다.	
	//진행순서 : 매핑주소 요청발생 > 1)preHandle 메서드 > 2)컨트롤 url주소의 메서드가 진행됨 > 3) postHandle 메서드 > 4) afterCompletion 메서드
	
// (1)preHandle메서드: Controller로 요청이 들어가기 전에 가로채어 다음 메서드가 호출(실행)된다.
	//	  리턴값이 true가 되면, 컨트롤러로 진행이 이뤄진다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("preHandle메서드 실행");
		
		boolean result = false;
		
		HttpSession session = request.getSession();
		UserInfoVO vo = (UserInfoVO)session.getAttribute("login_status");
		
		if(vo == null) { //만약 로그인을 하지 않았으면

			//원래 요청한 주소를 세션으로 저장하는 기능. 
			getTargetUrl(request); // 아래에서 수동으로 만든 페이지 저장 메서드 : 로그인 전에 있었던 페이지를 저장해놓는다.
		
			result = false;
			response.sendRedirect("/userinfo/login"); 
			// 로그인 안하고 WebMvcConfig.java에서 addPathPatterns로 지정한마이페이지에 접속하려 하면 로그인 페이지로 자동 이동
		}else {
			result = true;
		}
		return result;  // true가 나오면 로그인이 된것이므로 WebMvcConfig.java에서 차단했던 페이지로 넘어간다. 
	} 

	
	
// (2) postHandle메서드 :컨트롤러의 URL주소에 해당하는 메서드의 실행이 끝나고 return 값의 뷰(View) 화면을  처리하기 전에 이 메서드가 호출(실행)된다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.info("postHandle메서드 실행");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
	
// (3) afterCompletion메서드: 컨트롤러의 URI에 해당하는 메서드에서 참조하는 뷰(View) 화면처리가 완료된 후 호출된다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		log.info("afterCompletion메서드 실행");
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	
	
// (4) [원래 요청한 주소를 가지고 있는 기능]
	private void getTargetUrl(HttpServletRequest request) {
		
		String uri = request.getRequestURI();  //  /userinfp/mypage
		String query = request.getQueryString();  //   ?뒤의 문자열.
		
		if (query ==  null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}
		
		String targetUrl = uri + query;
		
		if(request.getMethod().equals("GET")) {
			request.getSession().setAttribute("targetUrl", targetUrl);   //요청한 주소에 대한 정보를 세션으로 저장해놓겠다.
		}
	}
	
	
}
