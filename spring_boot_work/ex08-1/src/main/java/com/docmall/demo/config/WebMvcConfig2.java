package com.docmall.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.docmall.demo.interceptor.AdminInterceptor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  
@Component
public class WebMvcConfig2 implements WebMvcConfigurer {

	private final AdminInterceptor adminInterceptor;
	
	//인터셉터 매핑주소 설정에서 제외되는 경로자업. 인터셉터가 동작되지 않게하는 주소.
	private static final String[] EXCLUDE_PATHS = {
			"/admin/intro",
			"/admin/admin_ok"
	};
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminInterceptor)		// 2) 이걸로 인터셉터 할건데
				.addPathPatterns("/admin/**")      		// 1) 여기에 해당하는 주소를
				// admin으로 시작하는 하위레벨 깊이에 상관없이 모든 주소를 설정
				.excludePathPatterns(EXCLUDE_PATHS);	// 3) EXCLUDE_PATHS에 있는 주소는 제외하고 인터셉터 하겠다.
				//위의 설정에서 제외되는 주소설정.				//   ※EXCLUDE_PATHS에 적지 않고 직접적인 주소를 기입해도 되지만
														// 	   제외한 주소를 한번에 보여주기 위해 따로 위에 빼놨다.
	}
}
