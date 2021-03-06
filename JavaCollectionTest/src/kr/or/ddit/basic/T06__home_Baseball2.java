package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class T06__home_Baseball2 {

	//Set을 이용해서 야구게임 만들기
	/*
		 Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
		  컴퓨터의 숫자는 난수를 이용하여 구한다. (1~9사이의 수)
		  (스트라이크는 'S', 볼은 'B'로 출력한다.)
		  
		  컴퓨터의 난수가 9 5 7 일때 실행 예시)
		  숫자입력 => 3 5 6
		  3 5 6 ==> 1S 0B
		  숫자입력 => 7 8 9
		  7 8 9 ==> 0S 2B
		
		  ...
		  숫자입력 => 9 5 7
		  9 5 7 ==> 3S 0B
		
		  5번째 만에 맞췄군요. 
	 */
	public static void main(String[] args) {
		Set<Integer> intRnd = new HashSet<>();
		
		int count = 0;
		
		while(intRnd.size() < 3) {
			int num = (int)(Math.random() * 9 + 1); //1~9사이의 난수 만들기
			intRnd.add(num);
		}
		//System.out.println("만들어진 난수들 : " + intRnd);
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			System.out.println("숫자 입력>");
			String numbers = sc.nextLine();
			
			String[] splNum = numbers.split(" ");
			
			//inputNum을 Set으로 만들면 오름차순으로 자동 정렬해줘서, List로 생성
			List<Integer> inputNum = new ArrayList<>();
			inputNum.clear(); //inputNum이 Set이라면 중복값을 허용하지 않으므로, 내용을 모두 삭제하고 입력받아야 한다.
			for(int i = 0; i < splNum.length; i++) {
				inputNum.add(Integer.parseInt(splNum[i]));
			}
			
			Iterator itr = intRnd.iterator();
			
			int strike = 0;
			int ball = 0;
			int out = 0;
			
			Iterator itr2 = inputNum.iterator();
			int cnt = 0;
			while(itr2.hasNext()) {
				if(itr.next() == itr2.next()) {
					strike++;
				}
			}
			
			List<Integer> intRndList = new ArrayList<Integer>(intRnd);
			if(intRndList.get(0) == inputNum.get(1) || intRndList.get(0) == inputNum.get(2)) ball++;
			if(intRndList.get(1) == inputNum.get(0) || intRndList.get(1) == inputNum.get(2)) ball++;
			if(intRndList.get(2) == inputNum.get(0) || intRndList.get(2) == inputNum.get(1)) ball++;

			out = 3 - strike - ball;
			
			System.out.println(++count + "차 시도(" + inputNum.get(0) + inputNum.get(1) + inputNum.get(2) + ") : "
					+ strike + "S " + ball + "B " + out + "O");
			System.out.println("----------------------------------------");
			
			if(strike == 3){
				System.out.println("정답입니다!!");
				break;
			}		
		}
	}
}
