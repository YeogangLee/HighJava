package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Lock 객체를 이용한 동기화 처리)
 * @author PC-14
 *
 */
public class T17_LockAccountTest {
/**
 * 락 기능을 제공하는 클래스
 * ReentrantLock: Read 및 Write 구분 없이 사용하기 위한 락 클래스로, 동기화 처리를 위해 사용된다.
 * 
 * - 락을 걸고 풀고의 장점
 * lock()을 안 풀면 계속 걸린 것, synchronized 보다 더 넓은 동기화 영역 지정 가능
 * but
 * unlock() 안 해주면 끝장난다.
 * 
 * 동기화는 언제 필요?
 * 2개 이상의 스레드가 공유 객체에 접근할 때
 * read만 일어나면 문제 발생X
 * but
 * 값 변경, 수정이 일어날 때는... 문제 발생
 */
	
	public static void main(String[] args) {
		//락 객체
		ReentrantLock lock = new ReentrantLock(true);
		
		//공유객체
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000); //입금 처리
		
		BankThread2 bth1 = new BankThread2(lAcc);
		BankThread2 bth2 = new BankThread2(lAcc);
		
		bth1.start();
		bth2.start();
	}
}

//공유객체, 업무보는 스레드 먼저 작성하고 메인으로

//입출금을 담당하는 클래스
class LockAccount {
	private int balance;
	
	// Lock 객체 생성 => 되도록이면 private final로 만든다.
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	public int getBalance() {
		return balance;
	}
	
	//입금하는 메서드
	public void deposit(int money) {
		//Lock객체의 lock()메서드가 동기화 시작이고,
		//unlock()메서드가 동기화의 끝을 나타낸다.
		//lock()메서드로 동기화를 설정한 곳에서는 반드시 unlock()메서드로 해제해 주어야 한다.
		
		lock.lock(); 	  //시작 (락을 획득하기 전까지 BLOCKED 됨)
		balance += money; //동기화 처리 부분
		lock.unlock();	  //해제
	}
	
	//출금하는 메서드 (출금 성공 : true, 출금 실패: false 반환)
	public boolean withdraw(int money) {
		lock.lock();  //락 획득
		boolean chk = false;
		
		//try ~ catch 블럭을 사용할 경우에는
		//unlock()메서드 호출은 finally 블럭에서 해야 한다.
		try {
			if(balance >= money) {
				for(int i = 1; i<=1000000000; i++) {}
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			}
		}catch(Exception e) {
			chk = false;
		}finally { //예외가 발생하든 하지 않든 반드시 실행할 부분, JDBC처럼
			lock.unlock(); //락 해제(반납)
		}
		return chk;
	}
}

//은행 업무를 처리하는 스레드
class BankThread2 extends Thread {
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println("스레드 안에서 result = " + result + ", balance = " + lAcc.getBalance());
	}
}

