package kr.or.ddit.basic;

public class LambdaTest01 {
/**
 * 람다식 => 익명함수를 생성하기 위한 식
 * 자바에서는 '매개변수를 가진 코드 블럭'
 * => 런타임시 => 익명구현객체로 생성된다.
 * 
 * 형식) 인터페이스명 객체변수명 = 람다식;
 * 
 * 람다식의 형식) (매개변수들...) -> { 처리할 코드들; ... }
 * 								  ㄴ 메서드의 본체, 몸체들...
 *  
 * 모든 코드를 람다식으로 만들 수 있는 것은 아니다.
 * => 람다식으로 변환할 수 있는 인터페이스는 추상메서드가 1개인 인터페이스만 변환할 수 있다.
 *    이런 인터페이스를 '함수적 인터페이스'라고 한다. ex. Runnable 객체 - void run()
 *    
 *    함수적 인터페이스 생성시 어노테이션: @FunctionalInterface
 */
	/*
	 * 람다식 몰라도 자바 구현에 어려움이 있는 건 아니지만..
	 * 인터넷에 올라온 코드들에는 적용한 코드들이 있다 -> 이해를 위해
	 */
	public static void main(String[] args) {
		//람다식을 사용하지 않은 경우
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<=10; i++) {
					System.out.println(i);
				}
			}
		});
		
		th1.start();	
		
		//람다식을 사용하는 경우
		Thread th2 = new Thread(
				()->{ //매개변수가 없어서 ()
					for(int i=1; i<=10; i++) {
						System.out.println("람다-" + i);
					}
				}
		);
		
		th2.start();
	}
}
