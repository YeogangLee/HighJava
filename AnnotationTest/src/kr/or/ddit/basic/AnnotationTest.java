package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(PrintAnnotation.id);
		
		//reflection 기능을 이용한 메서드 실행하기
		//선언된 메서드 목록 가져오기
		Method[] declaredMethods = Service.class.getDeclaredMethods();
		
		for(Method m : declaredMethods) {
			System.out.println(m.getName()); //메서드명
			
			PrintAnnotation printAnno = m.getDeclaredAnnotation(PrintAnnotation.class);
			for(int i=0; i<printAnno.count(); i++) {
				System.out.print(printAnno.value());
			}
			System.out.println(); //줄바꿈 처리
			
			//주석처리 한 밑의 코드 1줄과, 그 밑에 적어놓은 코드들은 동일한 기능 수행
//			m.invoke(new Service());
			//invoke: 호출하다, 실행하다
			
			Class<Service> clazz = Service.class;
			
			Service service;
			try {			
				service = (Service) clazz.newInstance(); //객체 생성
				m.invoke(service);	//메서드 실행
			}catch (InstantiationException e) {
				e.printStackTrace();
			}		
			
		}
		
	}
}
