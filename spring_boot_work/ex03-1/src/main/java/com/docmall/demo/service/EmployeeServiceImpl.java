package com.docmall.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docmall.demo.domain.EmployeeVO;
import com.docmall.demo.mapper.EmployeeMapper;

// 구현클래스: 인터페이스를 상속받는 클래스
@Service 
public class EmployeeServiceImpl implements EmployeeService {

	//스프링이 내부적으로 EmployeeMapper 인터페이스를 이용하는 객체를 생성
	// 그리고, 아래 객체에 주입(대입). 즉, 참조를 해줌. 이걸 "의존성 주입(Dependency Injection : DI)"
	@Autowired //주입 기능을 제공하는 어노테이션
	private EmployeeMapper employeesMapper;
	
	@Override
	public List<EmployeeVO> emp_list() {
		return employeesMapper.emp_list();
	}
	
	
}
