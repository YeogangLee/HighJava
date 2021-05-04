package kr.or.ddit.basic;

public class T09_ThreadDaemonTest {
	public static void main(String[] args) {
		
		//2.메인으로 와서 작성
		Thread autoSave = new AutoSaveThread();
		
		// 데몬스레드로 설정하기 (start()메서드 호출하기 전에 설정해야 한다.)
		// 데몬으로 할지 일반으로 할지는 start()메서드 호출하기 전에 설정해야 한다.
		autoSave.setDaemon(true);
		
		autoSave.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println("작업" + i);
				Thread.sleep(1000);
			}
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("메인 스레드 종료...");
	}
}
//1.클래스 먼저 만든다.
/**
 * 자동 저장 기능을 제공하는 스레드(3초에 한번씩 저장한다)
 * @author PC-14
 *
 */
class AutoSaveThread extends Thread {
	
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			save(); // 저장기능 호출
		}
	}
}