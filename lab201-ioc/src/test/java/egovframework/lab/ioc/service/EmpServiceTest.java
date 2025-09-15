package egovframework.lab.ioc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.lab.ioc.config.EmpConfig;

@RunWith(SpringJUnit4ClassRunner.class)
/*
@ContextConfiguration(locations = {
    "classpath*:META-INF/spring/context-common.xml",
    "classpath*:META-INF/spring/context-emp.xml"
    
    //,"classpath*:META-INF/spring/context-postprocessor.xml"   
    // 이 주석을 풀고 테스트 시 annotationEmpService 에 대해서는 delete 메서드에 @Debug 를 설정하였으므로 trace 로그가 출력될 것임.
    })
*/
@ContextConfiguration(classes = EmpConfig.class) // 스프링 빈 파일 설정파일 명시 
public class EmpServiceTest {

    // TODO [Step 1-6, 2-6] EmpServiceTest 작성
//	@Resource(name = "xmlEmpService") // xml 파일 설정
//	@Resource(name = "annotationEmpService") // 어노테이션 DI서비스 변경 
	@Resource(name = "javaConfigEmpService")
	EmpService empService;
	
	public EmpVO makeVO() {
		return makeVO(101);
	}

	public EmpVO makeVO(int empNo) {
		EmpVO vo = new EmpVO();
		vo.setEmpNo(empNo);
		vo.setEmpName("홍길동" + empNo);
		vo.setJob("개발자");
		return vo;
	}
	
	public void checkResult(EmpVO vo, EmpVO resultVO) {
		assertNotNull(resultVO);
		assertEquals(vo.getEmpNo(), resultVO.getEmpNo());
		assertEquals(vo.getEmpName(), resultVO.getEmpName());
		assertEquals(vo.getJob(), resultVO.getJob());
	}
	
	@Test
	public void testInsertEmp() throws Exception{
		EmpVO vo = makeVO();
		empService.insertEmp(vo);
		EmpVO resultVO = empService.selectEmp(vo);
		checkResult(vo, resultVO);
	}
	
	@Test
	public void testUpdateEmp() throws Exception{
		EmpVO vo = makeVO(102);
		empService.insertEmp(vo);
		vo.setEmpName("홍길순");
		vo.setJob("설계자");
		empService.updateEmp(vo);
		EmpVO resultVO = empService.selectEmp(vo);
		checkResult(vo, resultVO);
		
	}
	
	@Test
	public void testDeleteEmp() throws Exception{
		EmpVO vo = makeVO(103);
		empService.insertEmp(vo);
		empService.deleteEmp(vo);
		EmpVO resultVO = empService.selectEmp(vo);
		assertNull(resultVO);
	}
	
	@Test
	public void testSelectEmpList() throws Exception{
		List<EmpVO> resultList = empService.selectEmpList();
		int firstListSize = resultList.size();
		assertTrue(firstListSize > 100);
		assertEquals(1, resultList.get(0).getEmpNo());
		
		EmpVO empVO = new EmpVO();
		empVO.setEmpNo(1);
		empService.deleteEmp(empVO);
		
		resultList = empService.selectEmpList();
		
		assertEquals(firstListSize - 1, resultList.size());
		assertEquals(2, resultList.get(0).getEmpNo());
		assertEquals("EmpName 2", resultList.get(0).getEmpName());
		assertEquals("SALESMAN", resultList.get(0).getJob());
	}
}
