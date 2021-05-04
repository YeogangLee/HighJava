package kr.or.ddit.basic;

public class T12_ThreadYieldTest {
/**
 * yield() 메서드에 대하여...
 * 
 * 1. 현재 실행 대기 중인 동등한 우선순위 이상의 다른 스레드에게 실행 기회를 제공한다.(양보)
 * 2. 현재 실행중인 스레드의 상태를 Runnable 상태로 바꾼다.(Waiting이나 Blocked 상태로 바뀌지 않는다. - 바로 Runnable로 유지된다)
 * 3. yield() 메서드를 실행한다고 해서 현재 실행 중인 스레드가 곧바로 runnable 상태로 전이된다고 확신할 수 없다.
 * 
 * 나는 cpu를 더 쓸 수 있지만, 내가 쓸 수 있는 이 cpu양을 다른 스레드가 쓸 수 있도록 양보,
 * cpu를 양보함으로써 다른 스레드가 더 의미있는 작업을 할 수 있다고 판단될 때
 */

/*
 * 실행결과마다 달라지는 마지막 스레드
 * -> yield(), 양보를 한다해도 확실히 보장되지 않는다.
 *    vm 사정에 따라 양보가 될 수도 안 될 수도 있다.
 */
	
	public static void main(String[] args) {
		//2.메인 작성
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx2();
		
		th1.start();
		th2.start();
	}
}

//1.클래스 만들기
class YieldThreadEx1 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println("YieldThreadEx1 : " + i);
			yield(); //양보하기
		}
	}
}

class YieldThreadEx2 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println("YieldThreadEx2 : " + i);
		}
	}
}