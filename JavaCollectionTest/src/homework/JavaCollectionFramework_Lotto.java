package homework;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class JavaCollectionFramework_Lotto {
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new JavaCollectionFramework_Lotto().startLotto();		
	}	
	
	public void displayMenu(){
		System.out.println("============================");
		System.out.println("	Lotto 프로그램	");
		System.out.println("----------------------------");
		System.out.println(" 1. Lotto 구입");
		System.out.println(" 2. 프로그램 종료");
		System.out.println("============================");
		System.out.println("메뉴선택 : ");
	}
	
	public void startLotto(){
		
		while(true){			
			displayMenu();  // 메뉴 출력
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			switch(menuNum){
				case 1 : buyLotto();		// Lotto 구입
					break;
				case 2 : 					// 종료
					System.out.println("감사합니다");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}
	
	private void buyLotto() {
		System.out.println("로또 구입");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.println("금액 입력 : ");
		int money = scan.nextInt();
		int count = money/1000;
		showLotto(count);
		System.out.println("받은 금액은 "+money+"원이고 거스름돈은 "+(money%1000)+"원입니다.");
	}
	
	private void showLotto(int count) {
		Set<Integer> intRnd = new HashSet<Integer>();
		
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for(int i = 0; i < count; i++) {
			System.out.print("로또번호"+(i+1)+" ");
			intRnd.clear();
			while(intRnd.size() < 6) {
				int num = (int)(Math.random() * 45) + 1;
				intRnd.add(num);
			}
			Iterator it = intRnd.iterator();
			int cntComma = 0;
			while(it.hasNext()) {
				System.out.print(it.next());
				if(cntComma<5) {
					System.out.print(",");
					cntComma++;
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
