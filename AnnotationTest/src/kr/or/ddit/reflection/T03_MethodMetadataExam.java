package kr.or.ddit.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 클래스에 선언된 메서드의 메타정보 가져오기
 * @author PC-14
 *
 */
public class T03_MethodMetadataExam {
	public static void main(String[] args) {
		//Class 객체 생성
		Class<?> klass = SampleVO.class;
		
		//클래스에 선언된 모든 메서드의 메타데이터 정보 가져오기
		Method[] methodArr = klass.getDeclaredMethods();
		
		for(Method m : methodArr) {
			System.out.println("메서드명 : " + m.getName());
			System.out.println("메서드 리턴타입: " + m.getReturnType());
			
			//해당 메서드의 접근제어자 정보 가져오기
			int modFlag = m.getModifiers();
			System.out.println("메서드 접근제어자 : " + Modifier.toString(modFlag));
			
			//해당 메서드의 파라미터값 가져오기
			Class<?> paramArr = m.getTypeParameters();
			System.out.println("메서드 파라미터 타입 : ");
			for(Class<?> clazz : paramArr) {
				System.out.print(clazz.getName() + " | ");
			}
			System.out.println();
			
			//해당 메서드에서 던지는 예외타입 가져오기
			Class<?> exTypeArr = m.getExceptionTypes();
			System.out.print("메서드에서 던지는 예외타입 목록 : ");
			for(Class<?> clazz : exTypeArr) {
				System.out.println(clazz.getName() + " | ");
			}
			System.out.println();
			
			//해당 메서드에 존재하는 annotation 타입 정보 가져오기
			Annotation[] annos = m.getAnnotations();
			System.out.println("Annotation 타입 : ");
			for(Annotation anno : annos) {
				System.out.println(anno.annotationType() + " | ");
			}
			System.out.println();
		}
	}
}
