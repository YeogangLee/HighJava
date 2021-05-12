package kr.or.ddit.basic;

//@PrintAnnotation(value="#", count=30) //클래스에 붙이면 바로 빨간줄이 그인다.
public class Service {
	
	@PrintAnnotation	//둘 다 매개변수가 없으므로, default값
	public void method1() { 
		System.out.println("메서드1에서 출력되었습니다.");		
	}

	//value 혼자 단독으로 쓰일 때는, 아래처럼 타입명 생략 가능
//	@PrintAnnotation("%") //count값은 주지 않았으므로, 디폴트값(20)이 지정된다
	@PrintAnnotation(value="%") //count값은 주지 않았으므로, 디폴트값(20)이 지정된다
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="#", count=30)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
	
}
