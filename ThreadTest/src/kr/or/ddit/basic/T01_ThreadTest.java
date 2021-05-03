package kr.or.ddit.basic;

public class T01_ThreadTest {
	public static void main(String[] args) {
		//싱글 스레드 프로그램
		for(int i = 0; i <= 200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for(int i = 0; i <= 200; i++) {
			System.out.print("$");
		}
		//실행이 끝나고 죽어요
		
		//실행결과의 특징
		//순서대로, *201개 찍고, 엔터 찍고, $201개 찍고, 다했네 끝 -> 메서드 종료
	}
}
