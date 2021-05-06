package kr.or.ddit.basic;

public class T19_WaitNotifyTest {
/*
 * 빵을 가지러 온 사람이 들어왔을 때, 거기 빵이 없다면, 그 사람은 할일이 없다
 * 그리고 그 사람 뒤로 사람들이 못 들어오고 기다리고 있다.
 * -> wait(), notify()의 필요성
 */
/**
 * wait()메서드: 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별로 존재함, 인스턴스마다 가상의 대기실이 존재한다.)으로 이동시킨다.
 * notify(),notifyAll()메서드: Wait-Set영역에 있는 스레드를 깨워서 실행될 수 있도록 한다.
 *                           (notify()는 하나, notifyAll()은 Wait-Set에 있는 전부를 깨운다.)
 * => wait(),notify(),notifyAll()
 *    : 동기화 영역에서만 실행할 수 있고, Object클래스에서 제공하는 메서드이다.
 *    -> 그래서 모든 객체가 가지고 이 메서드들을 가지고 있지만, 동기화 영역에서만 의미있게 사용될 수 있다.
 *    
 * notify()는 특정 스레드를 지정해서 깨울 수 없다, 랜덤으로 깨운다.
 * 한 스레드만 깨우면 문제가 생길 수 있으므로, 그냥 다 깨운다. -> notifyAll()
 * 일단 다 깨우고 나면 필요한 스레드가 들어오겠지.
 * 
 * 스레드A가 먼저 종료하고, 스레드B는 대기실에 있는 상태에서 프로그램 종료
 */
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

//공유객체 먼저

//공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 작업 중...");
		
		notify();
		
		try {
			wait(); //대기실에서 기다리는 개념, 누군가 깨워줄 때까지 기다린다.
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 메서드 작업 중...");
		
		notify();
		
		try {
			wait();
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}

//WorkObject의 methodA()메서드만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료.");
	}
}

//WorkObject의 methodB()메서드만 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료.");
	}
}