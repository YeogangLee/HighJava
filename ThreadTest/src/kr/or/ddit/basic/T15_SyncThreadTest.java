package kr.or.ddit.basic;

public class T15_SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번스레드", sObj);
		WorkerThread th2 = new WorkerThread("2번스레드", sObj);
		
		th1.start();
		th2.start();
		
		/*
		 * 스레드 합계 출력시,
		 * 스레드 간 순서가 없고, 합계 값들도 일정하게 증가하지 않는 모습 -> 임계 구역  
		 * 
		 * 임계구역 Critical Section
		 * : 병렬컴퓨팅에서 둘 이상의 스레드가 동시에 접근해서는 안되는 공유 자원을 접근하는 코드의 일부
		 * -> 문제가 많은 상황
		 * 
		 * 스레드가 1개일 때는 이런 문제가 발생X
		 * 스레드가 많아지면 많아질수록, 값이 서로 들어와 작업을 하면 순서가 없이 난장판이 된다.
		 * 해결 : 동기화 Synchronized
		 * 
		 * 이제 이런 문제 발생시 동기화 작업을 고려해야 하는 상황이 온 거에요.
		 * critical section에 동기화 처리
		 * 함수 제일 앞에 또는 반환형 앞에 선언 - synchronized
		 * ex1. synchronized public void add()
		 * ex2. public synchronized void add()
		 * 
		 * synchronized -> 이후에는 한 스레드만 작업 진행 가능, 작업 중에는 다른 스레드가 접근 불가
		 */
	}
}

//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 방법1: synchronized 키워드 추가, 메서드 자체에 동기화 설정
	//어떤 스레드가 동기화 메서드에 진입하는 순간, 다른 스레드들은 공유객체에 접근 불가
//	synchronized public void add() {
	public void add() {
		
		//동기화 방법2: 동기화 블럭 설정
		//현재는 전체 영역 동기화 - 방법1 동기화 메서드와 비슷하다
		
		//동기화 메서드는 동기화 영역이 메서드 전체
		//동기화 블럭은 동기화 영역 조정 가능 but 영역을 벗어나는 순간 동기화가 풀린다
		//동기화 블럭의 영역은 최소화 시키는 게 좋다, 영역을 넓히면 동기화가 확실해서 좋을 순 있어도 속도가 느려진다.
		synchronized (this) {
			for(int i=0; i<1000000000; i++) {} //동기화처리 전까지 시간 벌기용
			
			int n = sum;
			n += 10; //10 증가
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	}
	
	public int getSum() {
		return sum;
	}
}

//작업을 수행하는 스레드
class WorkerThread extends Thread {
	ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			sObj.add();
		}
	}
	
	
}