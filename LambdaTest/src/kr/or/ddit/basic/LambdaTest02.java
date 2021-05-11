package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest02 {
	public static void main(String[] args) {
		//람다식을 사용하지 않았을 경우
		LambdaTestInterface1 lam1 = new LambdaTestInterface1() {
			@Override
			public void test() {
				System.out.println("안녕하세요.");
				System.out.println("익명 구현 객체 방식입니다.");
			}
		};
		
		lam1.test(); //메서드 호출
		
		LambdaTestInterface1 lam2 =
				//1줄 경우: {}, ; 생략 가능
//				()-> System.out.println("반가워요\n람다식으로 처리하는 방식입니다.")
				
				//여러줄 경우
				()-> { System.out.println("반가워요\n람다식으로 처리하는 방식입니다.");
						System.out.println("ㅋㅋ");
				};
			
		lam2.test(); //메서드 호출
		System.out.println("---------------------------------------------");
		
/*
 * 람다식 작성 방법
 * 
 * 기본형식) (자료형 이름 매개변수명, ...) -> { 실행문들; ... }
 * 	1) 매개변수의 '자료형이름'은 생략할 수 있다.
 * 	예) 
 * 		(int a) -> { System.out.println(a); }
 * 		    (a) ->  { System.out.println(a); }
 * 
 *		//추상 메서드가 1개라서 타입 유추 가능
 * 
 * 	2) 매개변수가 1개일 경우에는 괄호 '()'를 생략할 수 있다.
 *     (단,'자료형이름'을 지정할 경우에는 괄호를 생략할 수 없다.)
 *      ex. int a => X (int a) => O
 *  예)
 *  	a -> { System.out.println(a); }
 *  
 *  3) '실행문(Statement)'이 1개일 경우에는 '{ }'를 생략할 수 있다.
 *     (이때 문장의 끝을 나타내는 세미콜론(;)도 생략한다.)
 *  예)
 *  	a -> System.out.println(a)
 * 
 *  4) 매개변수가 하나도 없으면 괄호 '()'를 생략할 수 없다.
 *  	(): 파라미터가 하나도 없다는 것을 명시적으로 알려줌
 *  예)
 *  	() -> System.out.println("안녕")
 * 
 *  5) 반환값이 있을 경우에는 return 명령을 사용한다.
 *  예)
 *  	(a, b) -> { return a + b; }
 *  	(a, b) -> return a + b 		//3번 적용 후
 *  
 *  6) 실행문에 return 문만 있을 경우 return 명령과 '{ }'를 생략할 수 있다.
 *  예)
 *  	(a, b) -> a + b 
 */
/*
 * Statement
 * n.성명서
 * 
 * - 위키백과 Statement
 * 문(프로그래밍): 명령형 프로그래밍 언어의 가장 작은 독립 요소
 */
		
		LambdaTestInterface2 lam3 = 
			(int z) -> {
				int result = z + 100;
				System.out.println("result = " + result);
			};
		lam3.test(30);
			
		LambdaTestInterface2 lam4 = 
			z -> {
				int result = z + 300;
				System.out.println("result = " + result);
			};
		lam4.test(60);
		
		LambdaTestInterface2 lam5 = 
			z -> System.out.println("result = " + (z + 500));
			//이때는 뒤에 문장이 있어서 실행문이 1문장이지만, 세미콜론(;) 생략 불가
		lam5.test(90);
		
		System.out.println("----------------------------------");
		
		LambdaTestInterface3 lam6 =
				(int x, int y) -> {
					int r = x + y;
					return r;
				};
		int k = lam6.test(20, 50);
		System.out.println("k = " + k);
		
		LambdaTestInterface3 lam7 =
				(x, y) -> {
					return x + y;
				};
		lam7.test(80, 50);
		System.out.println("k = " + k);
		
		LambdaTestInterface3 lam8 =
				(x, y) -> x + y;
				//리턴 사용시
//				(x, y) -> { return x + y; };
		k = lam8.test(100, 200);
		System.out.println("k = " + k);
		
		LambdaTestInterface3 lam9 =
				(x, y) -> { return x > y ? x : y; };
		k = lam9.test(100, 200);
		System.out.println("k = " + k);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		//forEach를 사용하기 위해선, Consumer 타입의 객체를 파라미터로 넘겨야 한다.
		//Consumer 타입? - 함수적 인터페이스
		//java doc, Interface Consumer<T>
		//This is a functional interface
		//and can therefore be used as the assignment target for a lambda expression or method reference.
		
		//람다식 - 자바8부터 지원, 함수적 인터페이스인 경우 바로 값을 넣어준다.
		list.forEach(x -> System.out.println(x));
	}
}









