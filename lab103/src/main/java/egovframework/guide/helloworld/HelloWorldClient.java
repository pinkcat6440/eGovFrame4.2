package egovframework.guide.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldClient { // 프로젝트의 메인 클래스
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldClient.class);

	/**
	 * @param args 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
				
		// xml파일에 helloworld 빈을 생성함
		String configLocation = "context-helloworld.xml"; 
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);

		// helloworld 빈을 가져와서 (HelloWorldService) 타입으로 캐스팅
		HelloWorldService helloworld = (HelloWorldService)context.getBean("helloworld");
		
		// HelloWorldService.java 의 sayHello메소드 실행하는 로그 찍음
		// 콘솔에 찍히는 내용 : pink Hello egov framework!!!
		LOGGER.debug(helloworld.sayHello());
	}

}
