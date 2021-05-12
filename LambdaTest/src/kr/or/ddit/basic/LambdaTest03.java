package kr.or.ddit.basic;

public class LambdaTest03 {
	static int stVar = 9;
	private String name = "aaa";
	
	public void testMethod(final int temp) {
//		final int localVar = 50;
		int localVar = 50;
		int kor = 100;
		
		/*
		 * 람다식 내부에서 사용되는 지역변수는 모두 final이어야 한다. final - 상수
		 * 보통은 final을 붙이지 않으면 컴파일러가 자동으로 붙여준다.
		 * 단, 지역변수의 값을 변경하는 식이 있을 경우에는
		 * 자동으로 final을 붙여주지 않는다.
		 */
		
//		temp = 500;
//		localVar = 2000;	//final을 지우면 에러x지만, 밑에서 에러 발생
//		kor = 400;
		
		//람다식에서 지역변수 사용하기
		LambdaTestInterface1 lt =
			() -> {
				//temp, localVar, kor 전부 final상태여야 에러발생X
				System.out.println("temp = " + temp);			
				System.out.println("localVar = " + localVar); //final을 써야 여기서 에러발생X	
				System.out.println("kor = " + kor); 	
				System.out.println("stVar = " + stVar);
				System.out.println("name = "+ this.name); //멤버변수, 지역변수x
			};
		lt.test();		
		
		//익명객체
		/*
		 * 만들어지는 시점에 값이 바뀌면 안된다
		 */
	}
	
	public static void main(String[] args) {
		new LambdaTest03().testMethod(200);
	}	
	
}
