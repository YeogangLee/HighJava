package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
	Annotation에 대하여...
	
	프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것
	(JDK1.5부터 지원)
	"마치 주석처럼" 프로그래밍 언어에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보를 제공한다.
	
	종류 : 1. 표준 애너테이션(주로 컴파일러에게 유용한 정보를 제공하기 위한 애너테이션)
		  2. 메타 애너테이션(애너테이션을 위한 애너테이션, 즉 애너테이션을 정의할 때 사용하는 애너테이션)
			  메타: 데이터의 데이터
			ex.테이블 생성시 컬럼을 정의, 이름을 위해 varchar타입의 컬럼 생성
			그 컬럼이 어떤 타입의 데이터를 받을지, varchar로 받겠다고 정의
			기본 데이터를 정해놨는데, 이 데이터를 설명하기 위한 또 다른 데이터가 필요하다 - 메타데이터
			
	애너테이션 타입 정의하기
	@interface 애너테이션이름 {
		요소타입 타입요소이름(); //반환값이 있고 매개변수는 없는 추상메서드의 형태, 그렇다해서 추상메서드는 아니다.
		...
	}
	
	애너테이션 요소의 규칙
	1. 요소의 타입은 기본형, String, enum, annotation, class만 허용된다.
	2. ()안에 매개변수를 선언할 수 없다.
	3. 예외를 선언할 수 없다.
	4. 요소의 타입에 타입 매개변수(제너릭타입 문자)를 사용할 수 없다.
 */

//@Target, @Retention: 어노테이션에 또 어노테이션을 붙임, 메타 어노테이션
@Target(ElementType.METHOD) //annotation이 적용 가능한 대상을 지정함.
@Retention(RetentionPolicy.RUNTIME)	//유지기간 지정함.(기본값: CLASS), retention n.유지기간
									//SOURCE, CLASS, RUNTIME 중 RUNTIME이 유지기간이 가장 길다
public @interface PrintAnnotation {
	int id = 100;				//상수 선언 가능. 사실상 의미 -> static final int id = 100; -> 스태틱 변수 선언 가능
	String value() default "-"; //기본값을 '-'로 지정
	int count() default 20;
}