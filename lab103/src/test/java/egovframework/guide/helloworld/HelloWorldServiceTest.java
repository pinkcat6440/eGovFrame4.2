package egovframework.guide.helloworld;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context-helloworld.xml"}) 
public class HelloWorldServiceTest{
	
	private HelloWorldService helloworld;
	
	@Resource(name = "helloworld")
	public void setHelloWorld(HelloWorldService hello) {
		this.helloworld = hello;
	}
	
	@Test
	public void sayHello() {
		assertEquals("pink Hello eGovFrame!!!", helloworld.sayHello());
	}
}


/*
//jUnit테스트 클래스 작성 
//내부적으로 스프링 컨테이너를 생성함, 생성된 컨테이너에 빈을 추가하기 위해서 설정 파일을 읽을 수 있어야 하는데..
@RunWith(SpringJUnit4ClassRunner.class)

//설정파일 로드하는 어노테이션
//설정파일의 위치를 locations{}에 지정했기에, 생성된 스프링 컨테이너가, 이 xml 파일에서 빈을 읽을 수 있게 된다 
//덕분에 xml에서 <context:annotation-config/> 이 태그를 생략 가능함
@ContextConfiguration(locations = { "/context-helloworld.xml"}) // src/test/resources 
public class HelloWorldServiceTest{
	
	// 이 필드는 테스트 대상인 인터페이스의 구현체를 저장하는데 사용  
	private HelloWorldService helloworld;
	
	// 스프링 컨테이너가 빈의 이름을 자동으로 찾아서 주입
	// @Resource이 스프링 컨테이너의 빈 중에서 helloworld 찾는다. 그 빈이 hello에 주입됨 
	@Resource(name = "helloworld")
	public void setHelloWorld(HelloWorldService hello) {
		this.helloworld = hello; // helloworld필드 값이 hello값으로 set 
	}
	
	@Test
	public void sayHello() {
		//첫번째 파라미터 : 예상 결과값 
		//두번째 파라미터 : 테스트 코드의 실제 코드값
		//이 두 파라미터의 값이 일치하면 테스트 성공 
		assertEquals("pink Hello eGovFrame!!!", helloworld.sayHello());
	}
}

*/
