package kr.or.ddit.basic;

public class T20_WaitNotifyTest {
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}

//공유객체, 스레드 클래스 정의 후 메인으로

//데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	//data가 null이 아닐 때, data값을 반환하는 메서드
	public synchronized String getData() {
		if(data == null) {
			try {
				wait();
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		//if절을 건너뛰고, data가 있는 경우
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);
		data = null;
		System.out.println(Thread.currentThread().getName() + " notify() 호출");
		//notify() 호출 이유, 대기실에서 기다리고 있을까봐
		notify();
		
		return returnData;
	}
	
	//data가 null일 경우에만 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) { //값이 있는 경우에는 set할 필요가 없으니, wait()으로 빠지기
			try {
				wait();
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		//값이 있는 경우 if문 건너뛰고 여기부터.
		this.data = data;
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + " notify() 호출");
		//아무 이유 없이 호출, 혹시나 대기실에 값 세팅해주는 애가 있다면 ...
		//값을 세팅하는 애도 왔다가 값이 있으면 할일이 없다.
		notify();
	}
}

//데이터를 세팅만 하는 스레드
class ProducerThread extends Thread {
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread"); //큰따옴표?
		//super("ProducerThread") : Thread 클래스의 생성자 호출
		//Thread는 문자열을 파라미터로 받는 생성자도 가지고 있다.
		//스레드 이름을 명시적으로 정의하고 싶을 때
		//-> super("스레드명")
		
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			String data = "Data-" + i;
			System.out.println("dataBox.setData(" + data + ") 호출");
			//호출했다는 게 진짜인지 알기 위해 출력 후 호출
			dataBox.setData(data);
		}
	}
}

//데이터를 읽어만 오는 스레드
class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread"); //스트링값을 파라미터로 받는 Thread 클래스의 생성자
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			String data = dataBox.getData();
			System.out.println("dataBox.getData() : " + data);
		}
	}
}