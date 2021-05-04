package kr.or.ddit.basic;

public class T13_ThreadStopTest {
/**
 * Thread의 stop()메서드를 호출하면 스레드가 바로 멈춘다.
 * 이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서
 * 나중에 실행되는 프로그램에 영향을 줄 수 있다.
 * 
 * 그래서 현재 stop()메서드는 사용을 권장하지 않는다.(디프리케이티드, 화살표 그어진 메서드, 호환성? 때문에 없애지는 못하고)
 */
	public static void main(String[] args) {
		
//		ThreadStopEx1 th = new ThreadStopEx1();
//		th.start();
//		
//		try {
//			Thread.sleep(1000);
//		}catch(InterruptedException ex) {
//			ex.printStackTrace();
//		}
//		
////		th.stop(); //컴퓨터 종료를 위해 코드 잡아빼는 방법
//		th.setStop(true);
//		
		
		//th2 실행할 때는 위에 코드 주석하고 실행
		//interrupt()메서드를 이용한 스레드 멈추기
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		th2.interrupt(); //th2에 인터럽트 걸기 - 중지시키기 위해, 무한루프에서 나오기 위해
	}
}

//stop으로 종료하는 방법
class ThreadStopEx1 extends Thread {
	private boolean stop;
	
	//나중에 true로 만들어주기 위해, setter를 들고옴
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 처리 중...");
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}

//인터럽트를 걸어서 중지하기 위한 별도의 클래스 만들기
//interrupt()메서드를 이용하여 스레드를 멈추는 방법
//interrupt 새치기, 끼어들다, 가로채다, 방해하다
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {

	/*
	 * 방법1 => sleep()메서드나 join()메서드 등을 사용했을 때
	 * 		   interrupt()메서드를 호출하면 InterruptedException이 발생한다.
	 */
		
	//방법1
/*
		try {
			while(true) {
				System.out.println("쓰레드 처리 중...");
				Thread.sleep(1); //예외 발생 역할 - 무조건 예외 발생은 아니고, 예외 발생 환경을 만들어주는
			}
		}catch(InterruptedException ex) {} //예외만 잡고 아무것도 하지 않음
		//try로 감싸서, while문에서 예외(InterruptedException)가 발생하면, 흐름이 바로 catch로 간다
		//catch로 가서 아무것도 안하고 빠져나온다
		//sleep(1): 예외 발생을 위해 적어줌, 이걸 적는다고 무조건 예외가 발생되는 건 아니고
		//1000 = 1초, 1초동안 잠자다가 다시 runnable상태로 바뀌는
		//sleep하고 있을 때 누가 interrupt를 걸면, 바로 저런 예외가 발생하도록 코딩을 한 것 
		//그래서 좋든싫든 sleep()메서드를 사용할 때마다 try-catch를 이용해서 감싸줬던 거다.
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
*/

		/*
		 * 방법2 => interrupt()메서드가 호출되었는지 검사하기
		 */
		while(true) {
			System.out.println("스레드 처리 중...");
			
			//검사방법1 => 스레드의 인스턴스 객체용 메서드를 이용하는 방법
			//isInterrupted() - Thread 클래스 내부 메서드
//			if(this.isInterrupted()) {//interrupt()메서드 호출되면 true
//				System.out.println("인스턴스용 isInterrupted()");
//				break;
//			}
			
			//검사방법2 => 스레드 클래스 내부의 정적static 메서드를 이용하는 방법
			//한 스레드만을 위한 메서드가 아니다.
			//내가 한번 interrupted() 사용해서 확인을 하면 초기값인 false로 바뀐다.
			//일회용 검사: 또 한번 호출하면 false가 출력이 된다. 이게 isInterrupted()방식과의 차이점
			//isInterrupted 호출은 누가 인터럽트 걸면 쭉 걸린 것
			if(Thread.interrupted()) {//interrupt() 메서드가 호출되면 true, interrupt()메서드 - Thread클래스 내부 메서드
				System.out.println("정적 메서드 interrupted()");
				break;				
			}
			
			/*
			 * 중지방법
			 * //stop() 제외하고
			 * 1.상태 flag값, 로직으로 종료되게 해주기, 원초적인 방법
			 * 2.interrupt 걸기
			 * 2-1.sleep()메서드, 자고있는 동안 누군가 interrupt를 걸 수 있기 때문에 예외를 발생시키도록 만들어졌다.
			 * 	      그 성질을 이용해서 무한루프를 종료
			 * 2-2.interrupt()메서드, 호출 여부 검사 - 인터럽트 걸렸는지 아닌지를 확인
			 * 검사방법1. 인스턴스 메서드        -> 스레드객체.isInterrupted(), true/false 결과값을 if문에서 이용
			 * 검사방법2. 정적static 메서드 -> 스레드객체.interrupted()
			 * 
			 */
			
		}		
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}