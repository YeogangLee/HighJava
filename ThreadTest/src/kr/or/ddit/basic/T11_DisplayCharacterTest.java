package kr.or.ddit.basic;
/**
 * 3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데,
 * 출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성하기
 * @author PC-14
 *
 */
public class T11_DisplayCharacterTest {
	static String strRank = ""; //순위 저장용 변수
	
	public static void main(String[] args) {
		Thread[] disChars = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("일지매"),
				new DisplayCharacter("변학도")
		};
		
		for(Thread th: disChars) {
			th.start();
		}
		
		for(Thread th: disChars) {
			try {
				//3개가 끝날때까지 join()을 이용해서 기다리기
				//실행이 끝나면 join()에서 기다리던 main스레드가 결과를 찍어준다
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝...");
		System.out.println("-----------------------------\n");
		System.out.println("== 경기 결과 ==");
		System.out.println("순위 : " + strRank);
	}
	
}

//1.캐릭터 출력 스레드 먼저 만들기
class DisplayCharacter extends Thread {
	private String name;
	
	//생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(name +"의 출력 문자 : " + ch);
			
			try {
				//sleep()메서드의 값을 200~500 사이의 난수로 한다.
				Thread.sleep((int)(Math.random()*301)+ 200);
				//200~500이면 범위 개수는 301개, 0부터 시작해서 200을 더해주면, 시작값은 200
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(name + " 출력 끝 ...");
		
		//나중에 static변수 지정해주면 strRank에 빨간줄 안 그인다.
		T11_DisplayCharacterTest.strRank += name + " ";
	}
	
}