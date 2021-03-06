package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class T06__home_Baseball {

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
		System.out.println("만들어진 난수들 : " + intRnd);
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			System.out.println("숫자 입력>");
			String numbers = sc.nextLine();
			
			String[] splNum = numbers.split(" ");
//			int[] inputNum = new int[3];
//			int[] ballNum = new int[3];
//			for(int i = 0; i < splNum.length; i++) {
//				inputNum[i] = Integer.parseInt(splNum[i]);
//			}
			
			Set<Integer> inputNum2 = new HashSet<>();
			inputNum2.clear();
			for(int i = 0; i < splNum.length; i++) {
				inputNum2.add(Integer.parseInt(splNum[i]));
			}
						
			int i = 0;
			Iterator itr = intRnd.iterator();
//			while(itr.hasNext()) {//다음 자료가 있는지 검사
//				ballNum[i] = Integer.parseInt(String.valueOf(itr.next()));
//				i++;
//			}
			
			int strike = 0;
			int ball = 0;
			int out = 0;
			
//			if(ballNum[0] == inputNum[0]) strike++;
//			if(ballNum[1] == inputNum[1]) strike++;
//			if(ballNum[2] == inputNum[2]) strike++;
//			
//			if(ballNum[0] == inputNum[1] || ballNum[0] == inputNum[2]) ball++;
//			if(ballNum[1] == inputNum[0] || ballNum[1] == inputNum[2]) ball++;
//			if(ballNum[2] == inputNum[0] || ballNum[2] == inputNum[1]) ball++;
			
			Iterator itr2 = inputNum2.iterator();
			int cnt = 0;
			while(itr2.hasNext()) {
//				System.out.println(itr.next());
//				System.out.println(itr2.next());
				if(itr.next() == itr2.next()) {
					strike++;
				}
			}
			
			//ball 계산을 위해 set -> list
			List<Integer> intRndList = new ArrayList<Integer>(intRnd);
			List<Integer> inputNumList = new ArrayList<Integer>(inputNum2);
			if(intRndList.get(0) == inputNumList.get(1) || intRndList.get(0) == inputNumList.get(2)) ball++;
			if(intRndList.get(1) == inputNumList.get(0) || intRndList.get(1) == inputNumList.get(2)) ball++;
			if(intRndList.get(2) == inputNumList.get(0) || intRndList.get(2) == inputNumList.get(1)) ball++;

			out = 3 - strike - ball;
			
			System.out.println(++count + "차 시도(" + inputNumList.get(0) + inputNumList.get(1) + inputNumList.get(2) + ") : "
					+ strike + "S " + ball + "B " + out + "O");
//			System.out.println(++count+"차 시도 : " + strike + "S " + ball + "B " + out + "O");
			System.out.println("----------------------------------------");
			
			if(strike == 3){
				System.out.println("정답입니다!!");
				break;
			}		
		}
	}
}
