package egovframework.lab.ioc.config;

import org.springframework.context.annotation.Bean;

import egovframework.lab.ioc.service.EmpService;
import egovframework.lab.ioc.service.impl.JavaConfigEmpDAO;
import egovframework.lab.ioc.service.impl.JavaConfigEmpServiceImpl;

public class EmpConfig {
//JavaConfig 설정	

	@Bean
	public JavaConfigEmpDAO javaConfigEmpDAO() {
		return new JavaConfigEmpDAO();
	}
	
	@Bean(name = "javaConfigEmpService")
	public EmpService javaConfigEmpService() {
		JavaConfigEmpServiceImpl empService = new JavaConfigEmpServiceImpl();
		
		// set으로 javaConfigEmpDAO 연결
		// common.xml의 이것과 동일 <property name="empDAO" ref="xmlEmpDAO" ></property>
		empService.setEmpDAO(javaConfigEmpDAO()); 
		return empService;
	}
	
}
