package kr.or.ddit.basic;

public class T08_ThreadPriority {
	public static void main(String[] args) {
		//우선순위를 정한다고 해서, 그 우선순위대로 흘러가지 않는다.
		//운영체제마다 virtual machine에 대한 다른 로직이 있을 것
		//그래서 정해준대로, 소문자가 제일 먼저 끝나고 대문자가 나중에 끝날거란 순서가 보장되지 않는다.
		//우선순위 관련 메서드: setPriority(), getPriority() - 기본값은 5(NORM_PRIORITY)
		
		Thread th1 = new ThreadTest1();
		Thread th2 = new ThreadTest1();
		Thread th3 = new ThreadTest1();
		Thread th4 = new ThreadTest1();
		Thread th5 = new ThreadTest1();
		
		Thread th6 = new ThreadTest2();
		
		//우선순위는 start()메서드를 호출하기 전에 설정해야 한다.
		//숫자가 클수록 우선순위가 높다.
		//우선순위 지정 - setPriority()
		th1.setPriority(1);
		th2.setPriority(1);
		th3.setPriority(1);
		th4.setPriority(1);
		th5.setPriority(1);
		th6.setPriority(10);
		
		//우선순위 가져오기 - getPriority()
		System.out.println("th1의 우선순위: " + th1.getPriority());
		System.out.println("th2의 우선순위: " + th2.getPriority());
		System.out.println("th3의 우선순위: " + th3.getPriority());
		System.out.println("th4의 우선순위: " + th4.getPriority());
		System.out.println("th5의 우선순위: " + th5.getPriority());
		System.out.println("th6의 우선순위: " + th6.getPriority());
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		th6.start();
		
		try {
			th1.join();
			th2.join();
			th3.join();
			th4.join();
			th5.join();
			th6.join();
			
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		//Thread 클래스 내부에 미리 정해져 있는 상수
		//F3으로 들어가보면 MAX: 10, NORM: 5, MIN: 1
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
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