package kr.or.ddit.basic;

import org.omg.Messaging.SyncScopeHelper;

public class T04_ThreadTest {
/**
 * 1 ~ 20까지의 합계를 구하는 데 걸린 시간 체크하기
 * 전체 합계를 구하는 작업을 단독으로 했을 때(1개의 쓰레드를 사용했을 때)와
 * 여러 쓰레드로 분할해서 작업할 때의 시간을 확인해 보자.
 */
	
	public static void main(String[] args) {
		//단독으로 처리할 때...
		SumThread sm = new SumThread(1L, 2000000000L);
		
		long startTime = System.currentTimeMillis();
		sm.start();
		
		try {
			//메인스레드는 여기서 sm스레드가 끝날 때까지 기다린다.
			sm.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리할 때의 처리 시간 : " + (endTime - startTime));
		System.out.println("\n\n");
		
		//여러 쓰레드가 협력해서 처리했을 때
		SumThread[] sumThs = new SumThread[] {
			new SumThread(1L,           500000000L),	//1부터 5억까지
			new SumThread(500000000L,  1000000000L),
			new SumThread(1000000000L, 1500000000L),
			new SumThread(1500000000L, 2000000000L)
		};
			
			startTime = System.currentTimeMillis();
			for(int i = 0; i < sumThs.length; i++) {
				sumThs[i].start();
			}
		
			for(SumThread s : sumThs) {
//				s.join(); //try-catch X??
/*//				try {
//					//join - 참여하다
//					//th의 run() 수행이 끝나고 콜스택은 비워지고 그 콜스택은 알아서 죽는다(사라진다)
//					//이후에 main이 순서대로 코드 실행 ...
//					s.join();
//					//현재 실행 중인 스레드에서 작업 중인 스레드(지금은 th스레드)가
//					//종료될 때까지 기다린다.
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
*/			}
			
			endTime = System.currentTimeMillis();
			System.out.println("협력 처리했을 때의 처리 시간 : " + (endTime - startTime));
			
		}
	}
}

class SumThread extends Thread {
	private long max, min;
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i=min; i<=max; i++) {
			sum += i;
		}
		System.out.println(min + " ~ " + max + "까지의 합 : " + sum);
	}
}