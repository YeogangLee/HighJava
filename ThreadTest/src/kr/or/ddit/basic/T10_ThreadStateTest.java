package kr.or.ddit.basic;

public class T10_ThreadStateTest {
/**
 * <스레드의 상태>
 * 
 * - NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
 * - RUNNABLE : 실행 중 또는 실행 가능한 상태
 * - BLOCKED : 동기화 블럭에 의해서 일시 정지된 상태(lock이 풀릴 때까지 기다리는 상태)
 * - WAITING, TIMED_WAITING : 스레드의 작업이 종료되지는 않았지만 실행 가능하지 않은 일시정지 상태(UNRUNNABLE).
 * 							  TIMED_WAITING은 일시정지 시간이 지정된 경우이다.
 * - TERMINATED : 스레드의 작업이 종료된 상태 
 */

/*
 * 스레드의 상태가 내부적으로 관리되고 있구나.
 * 스레드 호출 -> NEW -> RUNNABLE OR TIMED_WAITING -> 최종적으로 할일이 다끝나면 TERMINATED
 * 코드 실행시에 스레드의 상태가 바뀌고 있다. 하는 것을 알 수 있다.
 * 
 */
	
	public static void main(String[] args) {
		Thread th = new StatePrintThread(new TargetThread());
		
		th.start();
		
		
	}
}

//스레드의 상태를 출력하는 스레드 클래스(이 클래스도 스레드로 작성)
class StatePrintThread extends Thread {
	private Thread targetThread; //상태를 출력할 스레드가 저장될 변수
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			//Thread의 상태 구하기 (getState()메서드 이용)
			Thread.State state = targetThread.getState();
			//State는 enum상수, F3 타고 가면 State가 가질 수 있는 상수값을 볼 수 있다.
			//NEW, TERMINATED, TIMMED_WAITING
			System.out.println("타겟 스레드의 상태값 : " + state);
			
			//NEW 상태인지 검사
			//StatePrintThread가 targetThread를 start()시켜 준 것
			if(state == Thread.State.NEW) {
				targetThread.start();
			}
			
			//타겟 스레드가 종료 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break; //while문 빠져나가고, 빠져나가는 순간 run()종료, 얘도 죽는다.
					   //그러면 main스레드가 남는데 사실상 얘가 제일 먼저 죽죠 
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//1.스레드 클래스 먼저 정의
//Target용 스레드 클래스
class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i=1; i<=1000000000L; i++) {} //시간 지연용
		
		try {
			Thread.sleep(1500); //잠든 상태 = TIMED_WAITING 상태
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//RUNNABLE 상태
		for(long i=1; i<=1000000000L; i++) {} //시간 지연용
	}
}