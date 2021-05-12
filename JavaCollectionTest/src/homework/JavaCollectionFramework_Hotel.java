package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class JavaCollectionFramework_Hotel {
	private Scanner scan = new Scanner(System.in);
	private Map<Integer, Guest> hs = new HashMap<>();
	
	public static void main(String[] args) {
		new JavaCollectionFramework_Hotel().startHotel();		
	}
	
	public void startHotel(){
		boolean state = true;
		displayState(state);
		
		while(true){			
			displayMenu();  // 메뉴 출력
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			switch(menuNum){
				case 1 : checkInHotel();		// 1.체크인
					break;
				case 2 : checkOutHotel();		// 2.체크아웃
					break;
				case 3 : checkHotelRoom();		// 3.객실 상태
					break;
				case 4 : 						// 종료
					state = false;
					displayState(state);
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}
	
	private void checkInHotel() {
		System.out.println("\n어느 방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNo = scan.nextInt();
		if(hs.get(roomNo) != null) {
			System.out.println("\n" + roomNo + "번 방은 이미 체크인이 완료되었습니다.");
		}else {
			System.out.println("\n누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 => ");
			String guestName = scan.next();
			hs.put(roomNo, new Guest(roomNo, guestName));
			System.out.println("\n" + guestName + "님이 체크인 되었습니다.");
		}
	}

	private void checkOutHotel() {
		System.out.println("\n어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNo = scan.nextInt();
		
		if(hs.get(roomNo) == null) {
			System.out.println("\n" + roomNo + "번 방에는 체크인 한 사람이 없습니다.");
		}else {
			System.out.println("\n" + roomNo + "번 방의 " + hs.get(roomNo).getGuestName() + "님이 체크아웃 되었습니다.");
			hs.remove(roomNo);
		}
	}
	
	private void checkHotelRoom() {
		if(hs.size() == 0) {
			System.out.println("\n현재 호텔에는 체크인 한 사람이 없습니다.");
		}else {
			Set<Integer> ks = hs.keySet();
			for(Integer roomNo : ks) {
				//1. Guest 클래스의 toString을 사용해서 출력
//				System.out.println(hs.get(roomNo));
				//2. println에 적어준 대로 일일이 지정해서 출력
				System.out.println("| 방번호 : " + roomNo + "\t| 투숙객 : " + hs.get(roomNo).getGuestName());
			}
			System.out.println();
		}
	}
	
	public void displayState(boolean state){
		if(state) {
			System.out.println("\n===========================================\n" 
								+ "호텔 문을 열었습니다.\r\n" + 
							   "===========================================\n");
		}else {
			System.out.println("\n===========================================\n" 
								+ "호텔 문을 닫습니다.\r\n" + 
							   "===========================================");
		}
	}
	
	public void displayMenu(){
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("[1.체크인  2.체크아웃  3.객실 상태  4.업무 종료]");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 => ");
	}
	
}

class Guest {
	int roomNo;
	String guestName;
	
	public Guest(int roomNo, String guestName) {
		super();
		this.roomNo = roomNo;
		this.guestName = guestName;
	}
	
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	@Override
	public String toString() {
		//방번호 : 101, 투숙객 : 홍길동
		return "| 방번호 : " + roomNo + "\t| 투숙객 : " + guestName;
	}
}
