package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 단일 스레드에서의 사용자 입력 처리 예제
 * @author PC-14
 *
 */
public class T05_ThreadTest {
	public static void main(String[] args) {
		//JOptionPane - 자바에서 기본적으로 제공되는, 스윙 등의 인터페이스?를 제공하는 class 
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.out.println("입력한 값은 " + str + "입니다.");
		
		for(int i = 10; i >= 1; i--) {
			System.out.println(i);
			
			try {
				Thread.sleep(1000); //1초 동안 잠시 멈춘다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//10초가 지나면 상태가 콘솔창에 <terminated>로 바뀐다
	}
	
	//스레드가 1개일 수도, 여러 개일수도 있다
	//스레드가 여러 개라면 모든 스레드가 끝나야 프로그램이 종료된다.
	
	
}
