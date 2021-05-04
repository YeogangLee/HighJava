package kr.or.ddit.basic;

public class T14_ThreadShareDataTest {
/*
 * 원주율을 구하는 스레드, 원주율을 화면에 출력해주는 스레드
 * 공유객체를 이용해서 ...
 * 
 * - 스레드에서 데이터를 공통으로 사용하는 방법
 * 1. 공통으로 사용할 데이터를 클래스로 정의한다.
 * 2. 공통으로 사용할 클래스의 인스턴스를 만든다.
 * 3. 이 인스턴스를 각각의 스레드에 넘겨준다.
 * 4. 각각의 스레드는 이 인스턴스의 참조값을 저장한 변수를 이용하여 공통데이터를 사용한다.
 * 
 * 예) 원주율을 계산하는 스레드가 있고, 계산된 원주율을 출력하는 스레드가 있다.
 * 	   원주율을 계산한 후 이 값을 출력하는 프로그램 작성하기
 * 	 (이때 원주율을 저장할 객체가 필요하다.)
 * 
 * 원주율이 계산되기 전에는 출력할 수가 없으므로,
 * 원주율이 계산됐는지 매번 체크를 해야 한다.
 * 
 * 두 개의 스레드가 공통객체를 바라보고 있다.
 * 공통 객체를 통해 두 스레드 간 커뮤니케이션이 일어나는 것
 */
	
	public static void main(String[] args) {
		//공통으로 사용할 객체의 인스턴스 생성
		ShareData sd = new ShareData();
		
		//처리할 스레드 객체 생성
		CalcPIThread cpt = new CalcPIThread(sd);
		PrintPIThread ppt = new PrintPIThread(sd);
		
		cpt.start();
		ppt.start();
		
		/*
		 * 결과값을 보니 8자리까지 맞다
		 * 정확도를 높이려면 실행횟수를 더 늘려주면 된다.
		 * 하지만 지금 이게 중요한 게 아니고 공유객체에 포커스.
		 */
	}
}

//공유객체 정의 먼저하고 main으로 돌아오기
//원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData {
	public double result; // 원주율이 저장될 변수
	
	/*
	 * a.휘발성의 [볼러틸] - 유지가 되지 않는 값
	 * volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다. - 최적화 포기, 원활한 프로그램 수행을 위해
	 * 			      즉, 값이 변경되는 즉시 변수에 적용시킨다.
	 *			      다중 스레드에서 하나의 변수가 완벽하게 한 번에 작동하도록 보장하는 키워드
	 * 			   (일종의 동기화)
	 * 
	 * 다중 스레드에서 하나의 변수가 완벽하게 한 번에 작동
	 * 무슨 의미?
	 * -> 원본데이터인 RAM에 접근해서 값을 들고오는 방식
	 * 
	 */
	
	//원주율 계산이 완료됐는지를 나타내는 변수
	volatile public boolean isOk = false;
	
	/*
	 * volatile 등장 -> 멀티스레드 프로그래밍을 하고 있음을 알 수 있다.
	 */
	
}

//원주율을 계산하는 스레드
class CalcPIThread extends Thread {
	private ShareData sd;
	
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	public void run() {
	/*
	 * 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ......) * 4;
	 * 			1  -  3  +  5  -  7  +  9  => 분모
	 *          0     1     2     3     4  => 2로 나눈 몫
	 */
		double sum = 0.0;
		for(int i=1; i<=1500000000; i+=2) {
			if(((i/2) % 2) == 0) { //2로 나눈 몫이 짝수이면 +
				sum += (1.0/i);
			}else { //2로 나눈 몫이 홀수이면 -
				sum -= (1.0/i);
			}
		}
		sd.result = sum * 4; // 계산된 원주율을 공통객체의 멤버변수에 저장
		sd.isOk = true; // 계산이 완료되었음을 나타낸다.
	}
}

//계산된 원주율을 출력하는 스레드
class PrintPIThread extends Thread {
	private ShareData sd;
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		while(true) {
			//원주율 계산이 완료될 때까지 기다린다.
			//isOk가 true가 되면 빠져나온다.
			if(sd.isOk) {
				break;
			}
		}
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.result);
		System.out.println("       PI : " + Math.PI);
	}
	
}






