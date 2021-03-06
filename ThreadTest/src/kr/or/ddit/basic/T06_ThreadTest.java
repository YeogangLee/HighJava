package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 멀티스레드를 활용한 카운트다운 처리 
 * @author PC-14
 *
 */
public class T06_ThreadTest {
	//입력 여부 확인을 위한 변수 선언
	//모든 스레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		//start() - 별도의 콜스택을 생성
		th1.start(); 
		th2.start();
		
		//실행 이후, main()이 할 일이 없으니(이후 코드가 없으니) 제일 먼저 죽고, 
		//그 다음 사용자 데이터를 입력받았으니 th1이 죽고,
		//if문을 돌면서 사용자한테 입력을 받았는지 체크하는 th2가 입력받은 걸 확인하고 죽는다.
	}
}

/**
 * 데이터를 입력받는 스레드
 * @author PC-14
 *
 */
class DataInput extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		//입력이 완료되면 inputCheck변수를 true로 변경
		T06_ThreadTest.inputCheck = true;
	
		System.out.println("입력한 값은 " + str + "입니다.");
	}
}

/**
 * 카운트다운을 처리하는 스레드
 */
class CountDown extends Thread {
	@Override
	public void run() {
		for(int i = 10; i >= 1; i--) {
			//입력이 완료되었는지 여부를 검사하고 입력이 완료되면
			//run()메서드를 종료시킨다. 즉, 현재 스레드를 종료시킨다.
			if(T06_ThreadTest.inputCheck == true) { //true라면 사용자 입력을 받은 것
				return; //run()메서드가 종료되면 스레드도 끝난다.
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//10초가 경과되었는데도 입력이 없으면 프로그램 종료
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); //프로세스를 종료, 스레드가 돌고 있든 말든 종료 -> 강제 종료
	}
}
