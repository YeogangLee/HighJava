//package homework;
//
///*
//	10마리의 말들이 경주하는 경마 프로그램 작성하기
//	말은 Horse라는 이름의 클래스로 구성하고,
//	이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
//	그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
//	기능이 있다.( Comparable 인터페이스 구현)
//	경기 구간은 1~50구간으로 되어 있다.
//	경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
//	
//	예)
//	1번말 --->------------------------------------
//	2번말 ----->----------------------------------
//	...
//	경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
//*/
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class search_RaceHorse {
//
//	static int strRank = 1;
//
//	public static void main(String[] args) {
//		List<Horse> list = new ArrayList<>();
//
//		list.add(new Horse("1번말"));
//		list.add(new Horse("2번말"));
//		list.add(new Horse("3번말"));
//		list.add(new Horse("4번말"));
//		list.add(new Horse("5번말"));
//		list.add(new Horse("6번말"));
//		list.add(new Horse("7번말"));
//		list.add(new Horse("8번말"));
//		list.add(new Horse("9번말"));
//		list.add(new Horse("10번말"));
//
//		for (Horse horse : list) {
//			horse.start();
//		}
//
//		for (Horse hs : list) {
//			try {
//				hs.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		Collections.sort(list);
//		System.out.println("경기끝 ....");
//		System.out.println("======================================================");
//		System.out.println();
//		System.out.println(" 경기 결과 ");
//
//		for (Horse horse : list) {
//			System.out.println(horse.getName1() + " " + horse.getRank() + "등");
//		}
//	}
//}
//
//class Horse extends Thread implements Comparable<Horse>{
//   private String name1;
//   int rank;
//   
//   public Horse(String name) {
//      this.name1 = name;
//   }
//   
//   public String getName1() {
//      return name1;
//   }
//
//   public void setName1(String name) {
//      this.name1 = name;
//   }
//
//   public int getRank() {
//      return rank;
//   }
//
//   public void setRank(int rank) {
//      this.rank = rank;
//   }
//
//
//	@Override
//	public void run() {
//		for (int i = 0; i < 5; i++) {
//			System.out.print("\n" + name1 + " ");
//			for (int j = 0; j < i; j++) {
//				System.out.print("-");
//			}
//			System.out.print(">");
//
//			for (int j = 4; j > i; j--) {
//				System.out.print("-");
//			}
//			try {
//				Thread.sleep((int)(Math.random() * 500) + 1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println(name1 + " 끝");
//
//		setRank(search_RaceHorse.strRank);
//		search_RaceHorse.strRank++;
//	}
//
//	@Override
//	public int compareTo(Horse hor) {
//		if (rank > hor.getRank()) {
//			return 1;
//		} else {
//			return -1;
//		}
//	}
//}