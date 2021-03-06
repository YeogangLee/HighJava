package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
 * - List와 Set의 차이점
 * 
 * 1. List
 *   - 입력한 데이터의 순서O
 *   - 중복되는 데이터 저장O
 *   
 * 2. Set
 *   - 입력한 데이터의 순서X
 *   - 중복되는 데이터 저장X
 */

public class T05_SetTest {
	public static void main(String[] args) {
		Set hs1 = new HashSet();
		
		//Set에 데이터를 추가할 때 list처럼 add()메서드 사용
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2); // int는 Object, Integer로 저장된다.
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		//1.Set 데이터 추가
		//Set은 데이터 순서X, 중복 허용X
		//그래서 이미 있는 데이터를 add하면 false를 반환하고, 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		isAdd = hs1.add("CC"); //중복되는 데이터
		System.out.println("중복될 때 : " + isAdd);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		//2.Set 데이터 수정
		//수정하는 명령이 따로 없기 때문에 해당 자료를 삭제한 후 새로운 데이터를 추가해야 한다.
		/*
		 * 1) clear() : Set 데이터 전체 삭제
		 * 2) remove(삭제할자료) : 해당 자료 삭제
		 */
		
		//예) "FF"를 "EE"로 수정하기
		hs1.remove("FF"); //list처럼 remove()사용
		System.out.println("삭제 후 Set 데이터 : "+hs1);
		System.out.println();
		
		hs1.add("EE"); //EE자료 추가
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		//3.삭제하기
		//hs1.clear(); //전체 자료 삭제, list에도 동일하게 존재
		//System.out.println("clear 후 Set 데이터 : " + hs1);
		System.out.println("Set의 자료 개수: "+hs1.size());
		System.out.println();
		
		//4.꺼내오기
		//Set은 데이터의 순서가 없기 때문에 List처럼 인덱스로 데이터를 불러올 수 없다.
		//-> Iterator 객체 이용

		//Set의 데이터를 Iterator로 변환하기 => Set의 iterator() 메서드 호출
		Iterator itr = hs1.iterator();
		
		//데이터 개수만큼 반복하기
		//hasNext() 메서드 => 포인터 다음 위치에 데이터가 있으면 true, 없으면 false를 반환한다.
		while(itr.hasNext()) {//다음 자료가 있는지 검사
			//next()메서드 => 포인터를 다음 자료 위치로 이동하고, 이동한 위치의 자료를 반환
			System.out.println("itr 사용 : "+itr.next()); //next()를 사용하려고 true 반환
		}
		//인덱스가 없으므로 get메서드를 사용할 수X, 이렇게 하나씩 순회하며 조회
		//Iterator 반복자는 hasNext()-체크용, next()-꺼내오기용 를 반드시 가지고 있다.
		
		//1~100 사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<>();
		
		while(intRnd.size() < 5) {//Set의 데이터가 5개가 될 때까지 반복
			//1~100사이의 난수 만들기
			int num = (int)(Math.random() * 100 + 1);
			intRnd.add(num);
		}
		System.out.println("만들어진 난수들 : " + intRnd);
		//5개의 정수들, 이 정수들은 중복되지 않음
		
		//Collection 유형의 객체들은 서로 다른 자료구조로 쉽게 변경해서 사용할 수 있다.
		//다른 종류의 객체를 생성할 때 생성자에 변경할 데이터를 넣어주면 된다.
		List<Integer> intRndList = new ArrayList<Integer>(intRnd);
		//생성자에 set객체를 넣어주면 알아서 list객체를 만들어준다. - 같은 Collection을 사용하기 때문
		System.out.println("List의 자료 출력...");
		
		System.out.println("- index 사용");
		for(int i = 0; i < intRndList.size(); i++) {
			System.out.print(intRndList.get(i) + " ");
		}
		System.out.println();
		System.out.println("- for each 문 사용");
		for(Integer num : intRndList) {
			System.out.print(num + " ");
		}
		
		//여기까지 Set의 CRUD 살펴봤어요
		
	}
}
