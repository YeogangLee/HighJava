package kr.or.ddit.basic;

public class T03_ThreadTest {
	public static void main(String[] args) {
		//스레드의 수행 시간 체크해 보기
		Thread th = new Thread(new MyRunner());
		
		//UTC(Universal Time Coordinated 협정 세계 표준시)를 사용하여
		//1970년 1월 1일 0시 0분 0초를 기준으로 경과한 시간을 밀리컨드(1초/1000) 단위로 나타낸다.
		//=> 유닉스 타임스탬프 
		long startTime = System.currentTimeMillis();
		
		th.start(); //스레드 작업 시작
		
		try {
			//join - 참여하다
			//th의 run() 수행이 끝나고 콜스택은 비워지고 그 콜스택은 알아서 죽는다(사라진다)
			//이후에 main이 순서대로 코드 실행 ...
			th.join();
			//현재 실행 중인 스레드에서 작업 중인 스레드(지금은 th스레드)가
			//종료될 때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : " + (endTime - startTime));
		
	}
}

class MyRunner implements Runnable {

	//1 ~ 1,000,000,000(10억)까지의 합계를 구하는 스레드
	@Override
	public void run() {
		long sum = 0;
		for(long i=1L; i<=1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}