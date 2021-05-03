package kr.or.ddit.basic;

public class T08_ThreadPriority {
	public static void main(String[] args) {
		
	}
}

//대문자를 출력하는 메서드
class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(ch);
		}
		
		//아무것도 하지 않는 반복문(시간 때우기용)
		for(long i=1; i<=1000000000L; i++) {} //CPU 사용 중

		//위의 for문과 Thread.sleep()과의 차이점 : CPU 사용 여부
//		try {
//			Thread.sleep(100); // CPU 사용 X
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}

//소문자를 출력하는 메서드
class ThreadTest2 extends Thread {
	@Override
	public void run() {
		for(char ch='a'; ch<='z'; ch++) {
			System.out.println(ch);
		}
		
		//아무것도 하지 않는 반복문(시간 때우기용)
		for(long i=1; i<=1000000000L; i++) {}
	}
}