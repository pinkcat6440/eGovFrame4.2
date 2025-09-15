package egovframework.guide.helloworld;

import org.springframework.beans.factory.annotation.Autowired;

public class HelloWorldServiceImpl implements HelloWorldService{

	//설정에서 제공되는 값이 주입대상이 됨, 
	//설정에 값을(필드명) 명시적으로 지정해두면 그 값이 set메서드를 통해 주입이 됨  
	private String name; 
	
	// Autowired를 통해 주입대상임을 표시함
	// 스프링 컨테이너가 해당 타입의(string) 빈을 자동으로 찾아서 주입
	@Autowired  
	public void setName(String name) {
		this.name = name;
	}
	
	// 인터페이스의 메소드를 구현함
	@Override  
	public String sayHello() { 
		return "Hello " + name + "!!!" ;
	}
}
