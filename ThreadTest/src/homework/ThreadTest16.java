//package homework;
//
//import java.util.Arrays;
//
//public class ThreadTest16 {
//
//	public static void main(String[] args) {
//
//		Horse[] horses = new Horse[] {
//				new Horse("01번말"), new Horse("02번말"), new Horse("03번말"), new Horse("04번말"), new Horse("05번말"),
//				new Horse("06번말"), new Horse("07번말"), new Horse("08번말"), new Horse("09번말"), new Horse("10번말") };
//		PlayState state = new PlayState(horses);
//		for (Horse h : horses) {
//			h.start();
//		}
//		state.start();
//		for (Horse h : horses) {
//			try {
//				h.join();
//			} catch (InterruptedException e) {
//			}
//		}
//		try {
//			state.join();
//		} catch (InterruptedException e) {
//		}
//		System.out.println();
//		System.out.println("경기 끝........");
//		System.out.println(); 
//		
//		 경기 종료 후 등수순으로 정렬하기 Arrays.sort(horses); System.out.println("경기 결과");
//		 for(Horse h : horses){ System.out.println(h); } }
//}
//
//class Horse extends Thread implements Comparable<Horse> {
//	public static int currentRank = 0;
//	private String horseName; // 말이름 private int rank; // 등수 private int
//	location; // 현재위치 // 생성자 public Horse(String horseName) { this.horseName =
//	horseName;
//	}
//
//	public String getHorseName() {
//		return horseName;
//	}
//
//	public void setHorseName(String horseName) {
//		this.horseName = horseName;
//	}
//
//	public int getRank() {
//		return rank;
//	}
//
//	public void setRank(int rank) {
//		this.rank = rank;
//	}
//
//	public int getLocation() {
//		return location;
//	}
//
//	public void setLocation(int location) {
//		this.location = location;
//	}
//
//	@Override
//	public String toString() {
//		return "경주마 " + horseName + "는" + rank + "등 입니다.";
//	}
//
//	@Override
//	public int compareTo(Horse h) {
//		return Integer.compare(rank, h.getRank());
//	}
//
//	@Override
//	public void run() {
//		for (int i = 1; i <= 50; i++) {
//			location = i;
//			try {
//				Thread.sleep((int) (Math.random() * 400));
//			} catch (InterruptedException e) {
//			}
//		}
//		rank = ++Horse.currentRank;
//	}} // 경기 중의 현재 상황을 출력하는 쓰레드 class PlayState
//	extends Thread{
//
//	private Horse[] horses; // 생성자 public PlayState(Horse[]
//	horses){this.horses=horses;
//	}
//
//	@Override public void run() { while(true){
//		 if(Horse.currentRank == horses.length){ // 경기가 종료되면... break; } for(int i=1;
//		 i<=10; i++){ System.out.println(); } for(int i=0; i<horses.length; i++){
//		 System.out.print(horses[i].getHorseName() + " : "); for(int j=1; j<=50; j++){
//		 if(horses[i].getLocation()==j){ // 현재위치 표시 System.out.print(">"); }else{
//		 System.out.print("-"); } } System.out.println(); } try { Thread.sleep(100); }
//		 catch (InterruptedException e) { // TODO: handle exception } } } }
//	}
//}
