package com.docmall.demo.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.demo.domain.UserInfoVO;
import com.docmall.demo.dto.EmailDTO;
import com.docmall.demo.dto.LoginDTO;
import com.docmall.demo.service.EmailService;
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
	
	//이메일 주입작업
	private final EmailService emailService;
	
	
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
		return "redirect:/userinfo/login";
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
				
				// 원래 요청한 주소가 세션으로 존재하면
				if(session.getAttribute("targetUrl") != null) {
					url = (String) session.getAttribute("targetUrl");
					}
				
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
	
	// [로그아웃]
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();	// 세션형태로 관리되는 모든 메모리는 소멸.
		
		return "redirect:/";
	}

	
	
	
	/*
	
	// [마이페이지]
	@GetMapping(value={"/mypage","/modify"})
	public void mypage(HttpSession session, Model model) throws Exception {
		
		String u_id = ((UserInfoVO) session.getAttribute("login_status")).getU_id();
		
		UserInfoVO vo = userInfoService.login(u_id);
		
		log.info("수정할 아이디 정보" + vo);
		model.addAttribute("userinfo", vo);  //userinfo라는 이름으로 mypage.jsp에서 사용
	}
	
	*/

	
	// [마이페이지]
	@GetMapping("/mypage")
	public void mypage(HttpSession session, Model model) throws Exception {
		
		String u_id = ((UserInfoVO) session.getAttribute("login_status")).getU_id();
		
		UserInfoVO vo = userInfoService.login(u_id);
		
		log.info("수정할 아이디 정보" + vo);
		model.addAttribute("userinfo", vo);  //userinfo라는 이름으로 mypage.jsp에서 사용
	}
	
	
	
	// [마이페이지 수정하기]
	@PostMapping("/modify")   //RedirectAttributes rttr는 수정하기 메세지 출력해주려구   //HttpSession session : 인증정보가 필요하므로 작성, 인증정보가 필요한 작업에는 반드시 매개변수로 들어가야 한다.
	public String modify(UserInfoVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		log.info("수정된 아이디 정보 " + vo);
		
		//인증된 사용자만 할 수 있는 작업에는 해당 코드가 모두 들어가야 함. 근데 코드가 길어지면..?
		// 이러한 스프링인터셉트 작업은 홈페이지 구성이 마무리 된 후 만든다.// 인터셉트보다 더 좋은 기능은 시큐리티다.
		if(session.getAttribute("login_status") == null) return "redirect:/userinfo/login";
		
		log.info("회원정보 수정" + vo);
		
		//인증된 사용자만 사용하게 하기 위해 해당 정보를 가져온다.
		String u_id = ((UserInfoVO) session.getAttribute("login_status")).getU_id();
		
		userInfoService.modify(vo);
		
		rttr.addFlashAttribute("msg","success"); //리턴에 있는 경로의 jsp에서 사용할 목적: 성공메세지 줄거임
		
		return "redirect:/userinfo/mypage";
	}
	
	
	// [비밀번호 변경 페이지]
	@GetMapping("/changepw")
	public void changepw() {
		
	}
	
	
	// [비밀번호 변경하기]
	@PostMapping("/changepw")
	public String changepw(String cur_pwd, String new_pwd, HttpSession session, RedirectAttributes rttr) {
	
		//세션작업 : 아이디 가져옴.
		String u_id = ((UserInfoVO) session.getAttribute("login_status")).getU_id();
		
		UserInfoVO vo = userInfoService.login(u_id);
		
		String msg = "";   
		
		if(vo != null) {  // 아이디가 존재한다면 (비밀번호도 존재한다 --> 비밀번호 비교작업)
			//비밀번호 비교 - u_id : 사용자가 입력한 비밀번호, vo.getU_pwd() : DB에 암호화된 비번
			if(passwordEncoder.matches(cur_pwd, vo.getU_pwd())) {  // 사용자가 입력한 비번이 암호화된 형태에 해당하는 것이라면?
						
					// [신규비밀번호 변경작업]
					String u_pwd = passwordEncoder.encode(new_pwd); // 암호화 작업
					userInfoService.changePw(u_id, u_pwd); // 암호화된 비번이 들어감.
					msg= "success";
					
				}else { //사용자가 입력한 비번이 암호화된 형태에 해당하지 않는 것이라면
					msg = "failPW";
				}
		}	
		rttr.addFlashAttribute("msg", msg);  //jsp에서 msg변수를 사용목적
		
		return "redirect:/userinfo/changepw";
	}
	
	//[회원탈퇴 페이지]
	@GetMapping("/delete")
	public void delete() {
		
	}
	
	// [회원탈퇴 기능]
	@PostMapping("/delete")
	public String delete(String u_pwd, HttpSession session, RedirectAttributes rttr) throws Exception {
		//세션작업 : 아이디 가져옴.
			String u_id = ((UserInfoVO) session.getAttribute("login_status")).getU_id();
			
			UserInfoVO vo = userInfoService.login(u_id);
			
			String msg = "";   
			String url = "";
			
			if(vo != null) {  // 아이디가 존재한다면 (비밀번호도 존재한다 --> 비밀번호 비교작업)
				//비밀번호 비교 - u_id : 사용자가 입력한 비밀번호, vo.getU_pwd() : DB에 암호화된 비번
				if(passwordEncoder.matches(u_pwd, vo.getU_pwd())) {  // 비밀번호 일치여부는 여기서 처리되었다.
							
						userInfoService.delete(u_id); // passwordEncoder.encode(u_pwd)는 암호화된 비번인데 똑같은 1234를 쓰더라도 암호화된게 모두 일치하지 않는다(매번 인코드 되기 때문). 이에 이걸 넣으면 안된다.
						session.invalidate(); // 회원탈퇴를 했으면 로그인상태도 해제해야한다. 즉 세션도 죽여야 한다. 
						msg = "success";
						url = "/";
						
					}else { //사용자가 입력한 비번이 암호화된 형태에 해당하지 않는 것이라면
						msg = "failPW";
						url = "/userinfo/delete";
					}
			}	
			rttr.addFlashAttribute("msg", msg);  //jsp에서 msg변수를 사용목적
	
			return "redirect:" + url;
	}
	
	
	// [아이디 찾기 화면]
	@GetMapping("/idfind")
	public void idfind() {
		
	}
	
	// [아이디 찾기]
	@PostMapping("/idfind")
	public String idfind(String u_name, String u_email, String authcode, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		String url = "";
		String msg = "";
						
		// EmailController.java와 비교하며 값을 넣어야 한다.
		// 인증코드 확인
		if(authcode.equals(session.getAttribute("authcode"))) {

			// 이름과 메일주소 확인.
			String u_id = userInfoService.idfind(u_name, u_email);
			if(u_id != null) {		

				//아이디를 내용으로 메일발송작업 (emailServiceImpl에서 기능을 가져와야한다. 주입작업을 해야한다★★★★★)
				String subject = "Docmall 아이디를 보내드립니다.";
				EmailDTO dto = new EmailDTO("DocMall", "DocMall", u_email, subject, u_id);
				
				emailService.sendMail("emailIDResult", dto, u_id);   //emailIDResult(타임리프 파일명)

				session.removeAttribute("authcode");  //세션 만료시키기
				
				msg = "success";
				url = "/userinfo/login";
				rttr.addFlashAttribute("msg", msg);
				
			}else {
				msg = "idFail";
				url = "/userinfo/idfind";
			}
			
		}else {
			msg = "failAuthCode";
			url = "/userinfo/findid";
			
		}	
		rttr.addFlashAttribute("msg", msg);
			
		return "redirect:" + url;
	}
	
	
	// [비밀번호 찾기 페이지]
	@GetMapping("/pwfind")
	public void pwfind() {
		
	}
	
	
	// [비밀번호 찾기 기능]
	@PostMapping("/pwfind")
	public String pwfind(String u_id, String u_name, String u_email, String authcode, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		String url = "";
		String msg = "";
		
		
		if(authcode.equals(session.getAttribute("authcode"))) {
			
			//사용자가 입력한 3개정보(아아디, 이름, 이메일)를 조건으로 사용하여, 이메일을 디비에서 가져온다.
			String d_email = userInfoService.pwfind(u_id, u_name, u_email);
			if(d_email != null) {
				
				//임시비밀번호생성(UUID이용)
				String tempPw = UUID.randomUUID().toString().replaceAll("-", "");  // - 를 제거
				tempPw = tempPw.substring(0, 10); //10자리
			
				//암호화 된 비밀번호
				String enc_tempPw = passwordEncoder.encode(tempPw); // 부작위로 뽑은 비밀번호 인코딩
				userInfoService.tempPwUpdate(u_id, enc_tempPw); //인코딩된 암호 db에 저장
				
				//메일발송
				EmailDTO dto = new EmailDTO("DocMall", "DocMall", d_email, "DocMall에서 입시 비밀번호를 보내드립니다.", tempPw); //변경안내 이메일에 들어갈 내용들
				
				emailService.sendMail("emailPwResult", dto, tempPw); // 비밀번호 변경 메일 발송
				
				session.removeAttribute("authcode");  //세션 제거
				
				url = "/userinfo/login";
			}else {
				url = "/userinfo/pwfind";
				msg = "failInput";				
			}
					
			//비밀번호 임시코드를 생성하여, 암호화해서, 사용자아이디에 저장한다.
			//그리고, 비밀번호 임시코드를 메일로 발급한다.
		}else {
			url = "/userinfo/pwfind";
			msg = "failAuth";	
		}
		
		return "redirect:" + url;  // 띄어쓰기 주의!! "redirect: " + url   ""사이에 공백 있었더니 페이지를 찾지 못함.
	}
	
	
	
	
	
	
	
	
	
	
}
	


