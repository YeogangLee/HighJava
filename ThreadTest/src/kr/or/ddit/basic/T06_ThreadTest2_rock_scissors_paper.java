package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 멀티스레드를 활용한 카운트다운 처리 
 * @author PC-14
 *
 */
public class T06_ThreadTest2_rock_scissors_paper {
	//입력 여부 확인을 위한 변수 선언
	//모든 스레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		Thread th1 = new DataInput2();
		Thread th2 = new CountDown2();
		
		//start() - 별도의 콜스택을 생성
		th1.start(); 
		th2.start();
		
		
	}
}

/**
 * 데이터를 입력받는 스레드
 * @author PC-14
 *
 */
class DataInput2 extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위바위보 : ");
		//입력이 완료되면 inputCheck변수를 true로 변경
		T06_ThreadTest2_rock_scissors_paper.inputCheck = true;
		
		boolean flag = false;
		int numCom = (int)(Math.random()*3)+1;
		int numUser = 0;
		if(str.equals("가위")) {
			numUser = 1;
			flag = true;
		} else if (str.equals("바위")) {
			numUser = 2;
			flag = true;
		} else if (str.equals("보")) {
			numUser = 3;
			flag = true;
		} else {
			System.out.println("잘못된 문자 입력");
			flag = false;
		}
		
		boolean flag2 = true;
		while(flag && flag2) {
			flag2 = false;
			String strCom = null;
			if(numCom == 1) {
				strCom = "가위";
			} else if (numCom == 2) {
				strCom = "바위";
			} else if (numCom == 3) {
				strCom = "보";
			}
			
			String result = null;
			if(numUser == 1 && numCom == 3) {
				result = "당신이 이겼습니다.";
			} else if(numUser == 3 && numCom == 1) {
				result = "당신이 졌습니다.";
			} else {
				if(numCom > numUser) {
					result = "당신이 졌습니다.";
				} else if (numCom < numUser) {
					result = "당신이 이겼습니다.";
				} else {
					result = "비겼습니다.";
				}
			}
			
			System.out.println("=== 결과 ===");
			System.out.println("컴퓨터 : " + strCom);
			System.out.println("당 신 : " + str);
			System.out.println("결 과 : " + result);
		}
		
	}
}

/**
 * 카운트다운을 처리하는 스레드
 */
//GameTimer
class CountDown2 extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {
			//입력이 완료되었는지 여부를 검사하고 입력이 완료되면
			//run()메서드를 종료시킨다. 즉, 현재 스레드를 종료시킨다.
			if(T06_ThreadTest2_rock_scissors_paper.inputCheck == true) { //true라면 사용자 입력을 받은 것
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
		System.out.println("5초가 지났습니다. 시간 초과로 당신이 졌습니다.");
		System.exit(0); //프로세스를 종료, 스레드가 돌고 있든 말든 종료 -> 강제 종료
	}
}
