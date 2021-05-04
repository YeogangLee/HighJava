package homework;
/*
 * 10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 */
public class JavaThread_HorseRacing {
	static String strRank;
	
	public static void main(String[] args) {
		
		Thread[] horses = new Horse[] {
			new Horse(1, "1번말"),
			new Horse(1, "2번말"),
			new Horse(1, "3번말"),
			new Horse(1, "4번말"),
			new Horse(1, "5번말"),
			new Horse(1, "6번말"),
			new Horse(1, "7번말"),
			new Horse(1, "8번말"),
			new Horse(1, "9번말"),
			new Horse(1, "10번말")
		};
		
		for(Thread horse: horses) {
			horse.start();
		}
		for(Thread horse: horses) {
			try {
				//기다리기
				horse.join();
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

class Horse extends Thread implements Comparable<Horse> {

	int rank;
	String horseName;
	
	public Horse(int rank, String horseName) {
		super();
		this.rank = rank;
		this.horseName = horseName;
	}

	@Override
	public int compareTo(Horse horse) {
		return this.getRank().compareTo(horse.getRank());
	}
	
	@Override
	public void run() {
		for(int i=0; i<=50; i++) {
			System.out.println("");
		}
		
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	
}
