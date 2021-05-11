package kr.or.ddit.basic;

@FunctionalInterface
//어노테이션을 쓰면 명시적으로 함수적 인터페이스가 되므로,
//내부에서 추상메서드를 1개 이상 생성시 컴파일 에러가 발생한다. -> 안전한 코드 
public interface LambdaTestInterface1 {
	//반환값이 없고 매개변수도 없는 추상 메서드 선언
	public void test();
//	public void test2();
}

@FunctionalInterface
interface LambdaTestInterface2 {
	//반환값이 없고 매개변수가 있는 추상 메서드
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3 {
	//반환값이 있고 매개변수도 있는 추상 메서드
	public int test(int a, int b);
}