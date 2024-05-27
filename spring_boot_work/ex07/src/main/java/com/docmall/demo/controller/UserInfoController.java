package com.docmall.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.demo.domain.UserInfoVO;
import com.docmall.demo.dto.LoginDTO;
import com.docmall.demo.service.UserInfoService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/userinfo/*")
//bean 생성 : userInfoService
@RequiredArgsConstructor
@Slf4j
@Controller   // userInfoController bean 자동생성
public class UserInfoController {
	
	//UserInfoServiceImpl를 사용하지 않고
	//UserInfoService 인터페이스를 사용한 이유는 다형성 목적
	private final UserInfoService userInfoService;
	
	//이 코드는 service단계에서 작업해줄것이므로 serviceImpl에 작성해줄것이다.
	//아래 코드는 학업용으로 남겨놓음. 
	private final PasswordEncoder passwordEncoder;
	
	// [회원가입폼]
	@GetMapping("/join")
	public void joinForm() {
		log.info("called joinForm..");
	}
	 
	// [아이디 중복체크]
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String u_id) throws Exception {
		
		log.info("아이디: " + u_id);
		
		ResponseEntity<String> entity = null;
		
		String idUse = "";
		
		if(userInfoService.idCheck(u_id) != null) {
			idUse="no";  //사용불가 (null이 아니면 DB에 값이 있는 것으로 사용 불가함.)
		}else {
			idUse = "yes"; //사용 가능(null이어야 DB에 값이 없는 것이므로 사용 가능인것)
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
													//HttpStatus.OK로 200번이 출력된다.
		return entity;
	}
	
	
	// [회원가입저장]
	// insert, update, delete작업을 하고, 다른 주소로 이동할 경우에는 메서드의 리턴타입은 String으로 해야한다.
	// 이유는? redirect: 사용해야 한다.
	// DB관련 작업은 오류발생을 대비하여 메서드 뒤에 throws Exception를 넣어준다
	@PostMapping("/join")
	public String join(UserInfoVO vo) throws Exception {
		
		log.info("회원정보 : " + vo);
		//log.info("비밀번호 : " + passwordEncoder.encode(vo.getU_pwd()));
						
		userInfoService.join(vo);
		
		//return "redirect:/이동주소";
		return "redirect:/list";
	}
	
	
	// [로그인폼]
	@GetMapping("/login")
	public void loginForm() {
		
	}
	
	//[로그인 작업]
	@PostMapping("/login") // 1)LoginDTO dto 	2) String u_id, String u_pwd
	public String login(String u_id, String u_pwd, HttpSession session, RedirectAttributes rttr) throws Exception {   //LoginDTO를 만들어 아래와 같이 작성해도 된다. 

		UserInfoVO vo = userInfoService.login(u_id);
		
		String msg = "";   // 로그인 폼 주소
		String url = "/";  // 메인주소		
		
		if(vo != null) {  // 아이디가 존재한다면 (비밀번호도 존재한다 --> 비밀번호 비교작업)
			//비밀번호 비교 - u_id : 사용자가 입력한 비밀번호, vo.getU_pwd() : DB에 암호화된 비번
			if(passwordEncoder.matches(u_pwd, vo.getU_pwd())) {  // 사용자가 입력한 비번이 암호화된 형태에 해당하는 것이라면?
				session.setAttribute("login_status", vo); 
				}else {
					msg = "failPW";
					url = "/userinfo/login";
				}
			}else { //아이디가 존재하지 않으면
				msg = "failID";
				url = "/userinfo/login";
			}
		rttr.addFlashAttribute("msg", msg);  //jsp에서 msg변수를 사용목적
		
		//log.info("url확인 "+ url);
		//log.info("msg확인 : " + msg);
		
		return "redirect:" + url;   //메인으로 이동
		}	
	
	}
	


