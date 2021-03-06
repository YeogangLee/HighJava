package kr.or.ddit.basic;

class Util2 {
	//- <T extends Number>로 제한했을 때 올 수 있는 것들
	//1.Number를 extends한 타입들 
	//2.Number 자체
	//생뚱맞게 String 이런거 못와요.
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
		//기본적으로 오름차순
		//누가 더 큰지 정해진 숫자를 리턴 : 앞 > 뒤 -- 1, 앞 = 뒤 -- 0, 앞 < 뒤 -- -1
	}
}

/**
 * 제한된 타입 파라미터(Bounded Parameter) 예제
 * @author PC-14
 * ex.String 타입을 상속받은 애들만 오게 하고 싶을 때 사용
 * 
 * 
 */

public class T04_GenericMethodTest {
	public static void main(String[] args) {
		int result1 = Util2.<Integer>compare(10, 20); //Integer없어도 compare()안 파라미터가 int이므로 컴파일러가 알아서 Integer로 처리
		System.out.println(result1);
		
		int result2 = Util2.<Number>compare(3.14, 3);
		System.out.println(result2);
		
//		Util2.compare("자바", "홍길동"); //String은 Number와 관련이 없으므로, 컴파일러가 바로 빨간줄로 알려준다.
		
	}
}
