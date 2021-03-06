package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03_ListSortTest {
	/*
	 * - 정렬 관련 Interface : Comparable, Comparator
	 * 객체 자체에 정렬 기능 -> Comparable
	 * 정렬 기준을 별도로 구현 -> Comparator
	 * 
  	 * Comparable : compareTo()
	 * Comparator : compare()
	 */

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");

		System.out.println("정렬 전 : "+list);
		
		//정렬 - Collections.sort()메서드
		//정렬은 기본적으로 '오름차순 정렬'
		//정렬 방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서
		//Collections.sort()메서드에 인수로 넘겨주면 된다.
		
		//Collection : Interface
		//Collections : Class
		Collections.sort(list); //오름차순
		//클래스에 메서드를 static으로 정의했기 때문에, 따로 객체 생성해서 사용할 필요x
		System.out.println("정렬 후 : " + list);
		
		Collections.shuffle(list); //데이터 섞기
		System.out.println("자료 섞기 후 : " + list);
		
		//정렬 방식을 결정하는 객체를 이용하여 정렬하기
		Collections.sort(list, new Desc()); //첫 번째 - 정렬 대상, 두 번째 - 정렬 방식
		System.out.println("정렬 후 : " + list);
		
		//내가 만든 클래스에 정렬 기능이 있는 별도의 기능을 만들고 싶다 - comparable
	}

}
/*
 * 정렬 방식을 결정하는 class는 Comparator라는 인터페이스를 구현해야 한다.
 * 이 Comparator 인터페이스의 compare()라는 메서드를 재정의 하여 구현하면 된다.
 */

class Desc implements Comparator<String> {
	
	/*
	 * compare() 메서드의 반환값을 결정하는 방법
	 * => 이 메서드가 양수를 반환하면 두 값의 순서가 바뀐다.(오름차순이 기본)
	 * 
	 *  - 오름차순 정렬일 경우
	 *  => 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환
	 *  
	 *  - String 객체에는 정렬을 위해서 compareTo() 메서드가 구현되어 있는데
	 *  이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
	 *  (Wrapper 클래스와 Date, File 클래스에도 구현되어 있다.)
	 */
	
	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2) * 1;
		//1을 곱해준 이유 : 나중에 -1을 곱하면, 로직이 반대가 되어 내림차순에 적용 가능
	}
}

//public class ClassMaker2 {
//	
//	//명시적 초기화
//	int a = 10;
//	
//	//인스턴스 변수, 초기화 블럭으로 초기화
//	{
//		a = 20;
//	}
//	
//	//인스턴스 변수, 생성자의 파라미터를 사용해 초기화
//	ClassMaker2(int a){
//		this.a = a;
//	}
//	
//	//생성자 하나 더 생성, 인스턴스 변수 초기화
//	ClassMaker2(int a, int b){
//		this(10); //생성자 호출은 생성자의 맨 첫 줄에 작성
//		
//		this.a = a;
//		this.a = b;
//	}
//	
//	public static void main(String[] args) {
//		System.out.println("a : "+ a);
//		System.out.println("a : "+ this.a);
//	}
//	
//}

