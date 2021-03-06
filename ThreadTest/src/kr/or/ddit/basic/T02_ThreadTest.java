package kr.or.ddit.basic;

public class T02_ThreadTest {
	
	public static void main(String[] args) {
		/* 프로세스 : 실행 중인 프로그램 */
		
		//멀티 스레드 프로그램 방식
		
		//방법1 : Thread 클래스를 상속한 class를 인스턴스를 생성한 후
		//		 이 인스턴스의 start() 메서드를 호출한다.
		//Thread 클래스가 있는데 우리는 그냥 이거 불러서 쓰면 된다.
		//나만의 클래스 정의하고 여기서 Thread 클래스 extends해서 써도 돼요.
		
		MyThread1 th1 = new MyThread1(); //스레드 객체 생성
		th1.start(); //실행 주체 : 메인 스레드
		
		//th1.run(); 으로 실행하면
		/*
		 * run()메서드를 실행하는 주체는 main thread이므로
		 * main thread가 지나가다가 run()메서드가 있네? *실행해주고, 그다음 $실행해주고, 그다음@실행해주고
		 * -> 우리가 원하는 모양이 아니죠.
		 */
		
		
		//방법2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		//		 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		//		 이때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r1 = new MyThread2(); //작업 내용은 별개로 이렇게 제공하려는 것 - r1 매개변수
		Thread th2 = new Thread(r1);
		th2.start();
		//현재 3개의 스레드가 동작 : main스레드, r1, th2
		
		
		//방법3 : 익명클래스를 이용하는 방법
		//		 Runnable 인터페이스를 구현한 익명클래스를 Thread 인스턴스를
		//		 생성할 때 매개변수로 넘겨준다.
		
		// 언제 써요?
		/*
		 * 일단 2,3번째 방법의 차이점은, 클래스를 따로 안 만들죠
		 * 왜? 한 번만 쓰고 말려구. 일시적으로 객체를 만들 때는 클래스를 만들 필요가X
		 * 
		 * 1,2? 
		 * 1은 run메서드 오버라이드, 근데 2,3은 runnable 인터페이스를 구현
		 * 
		 * 
		 * 1,3?
		 * 
		 * 첫 번째 방법 장점
		 * 심플, 편하다
		 * but 자바의 가장 큰 단점? 특징 > 다중 상속이 안 된다.
		 * 부모가 여러 부모일 수 없다.
		 * 내가 이미 상속을 받았으면 스레드 클래스를 상속받을 수 없다.
		 * 
		 * 그래서 Runnable 인터페이스로 상속받는 방법을 만들었겠죠.
		 * 
		 * - 왜 th1.run()이 아니고 th1.start()일까?
		 * -> run()을 사용하면 메인스레드가 실행을 시켜주는 것이기 때문에, 싱글스레드처럼 순차적으로 진행, *다찍고 $다찍고 @다찍고.
		 */
		
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100); //일시정지 상태
					}catch(InterruptedException ex){
						ex.printStackTrace();
					}
				}
			}
		});
		
		th3.start();
	}
}

class MyThread1 extends Thread {
	
	//Thread가 가지고 있는 메서드 중 가장 중요 : run()
	//앞으로 생성할 나만의 스레드들의 run()
	// = 메인 스레드의 main()
	@Override
	public void run() {
//		// TODO Auto-generated method stub
//		super.run();
		
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			try {
				//너무 빨리 찍히니까 잠깐 멈춰주기
				//Thread.sleep(시간) => 주어진 시간 동안 작업을 잠시 멈춘다.
				//시간은 밀리세컨드 단위를 사용한다.
				//즉, 1000은 1초를 의미
				Thread.sleep(100); //일시정지 상태
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100); //일시정지 상태
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
	}
}
