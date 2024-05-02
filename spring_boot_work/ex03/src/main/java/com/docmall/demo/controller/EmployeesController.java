package com.docmall.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.EmployeeVO;
import com.docmall.demo.service.EmployeesServiceImpl;

@RequestMapping("/employee/*")
@Controller
public class EmployeesController {
	// 로그객체
	private static final Logger logger = LoggerFactory.getLogger(EmployeesController.class);
	
	@Autowired // 의존성 주입(DI)
	private EmployeesServiceImpl employeeService;
	
	@GetMapping("emp_list") // employee/emp_list
	public void emp_list(Model model) {
		List<EmployeeVO> emp_list = employeeService.emp_list();
		model.addAttribute("emp_list", emp_list); // ("jsp에서 참조할 이름", 데이터)
	}
	
	
	
	
}
