package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class T01_ArrayListTest {
	public static void main(String[] args) {
		//Default Capacity = 10
		List list1 = new ArrayList();
//		List list1 = new Vector(); 
		//Vector도 리스트 타입, 인터페이스가 제공하는 메서드를 호출해가며 구현했기 때문에
		//이 인터페이스를 사용하는 객체는 오류X
		
		//add()메서드를 이용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111); //new Integer(111);
		//int를 Integer에 담아 객체화 -> 오토 박싱 처리 : 컴파일러가 알아서 객체화 시켜주는 것
		list1.add('k');
		list1.add(true);
		list1.add(12.34); // float ? double ? -> 기본으로 double, float로 하고 싶으면 f 붙여주기
		//컬렉션에 객체화 되어 들어가므로,
		//int로 넣었지만 Integer로 저장되어 있다.
		
		
		//java doc 8 검색
		//자바독
		
		//size() => 데이터 개수
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1);
		
		//데이터 꺼내오기(get 메서드)
		System.out.println("1번째 데이터 자료 : "+list1.get(1));
		
		//데이터 삽입
		list1.add(0, "zzz"); //인덱스, 값
		System.out.println("list1 => " + list1);
		
		//데이터 변경하기(set 메서드)
		String temp = (String)list1.set(0, "YYY");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		//삭제
		list1.remove(0);
		System.out.println("삭제 후 " + list1);
		list1.remove("bbb");
		System.out.println("bbb 삭제 후 " + list1);
		
//		list1.remove(111); //에러
		list1.remove(new Integer(111)); //에러X
		System.out.println("111 삭제 후 " + list1);
		System.out.println("=========================");
		
		//제너릭을 지정하여 선언
		List<String> list2 = new ArrayList<>(); //List - 제너릭 클래스
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for(int i=0; i<list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println();
		
		//향상된 for문, for each 문
		//단점 : index값을 이용할 수가 없다.
		//=> index 값을 이용할 때는 일반 for문 사용하기
		for(String s : list2) {
			System.out.println(s);
		}
		System.out.println("-------------------------");
		
		//contains(비교객체); => 리스트에 '비교객체'가 있으면
		//					true, 없으면 false가 리턴됨.
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		
		//indexOf(비교객체)
		//=> 리스트에서 '비교객체'를 찾아 '비교객체'가 있는
		//	 index 값을 반환한다. 없으면 -1 반환함.
		System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));
		System.out.println("-------------------------");
		
		//toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다.
		//				기본적으로 Object 배열 반환함. -> 원하는 자료형이 있으면 toArray(new 자료형)
		Object[] strArr = list2.toArray();
		System.out.println("배열의 크기 : " + strArr.length);
		
		//- 리스트의 제너릭 타입에 맞는 자료형의 배열로 변환하는 방법
		//제너릭 타입의 0개짜리 배열(크기가 없는 배열)을 생성해서 매개변수로 넣어준다.
		// => 배열의 크기가 리스트 크기보다 작으면, 리스트의 크기에 맞는 배열을 생성한다.
		String[] strArr2 = list2.toArray(new String[0]);
//		String[] strArr2 = (String[])list2.toArray();//배운 걸로 형변환 해보면, 오류
		System.out.println("strArr2의 개수 : " + strArr2.length);
		
		for(int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
		}
		
		//앞에서 지우기
		for(int i = 0; i < list2.size(); i++) {
			list2.remove(i);
		}
		System.out.println("list2 크기 : "+list2.size());
		//for문 써서 다 remove했는데 왜 2가 출력됐을까
		//list 크기가 하나씩 지울때마다 변경되기 때문에, 이빨 빠진 것처럼 지워진다. 
		
		//나중에 뒤에서 지우기 해보기
		for(int i = list2.size()-1; i >= 0; i--) {
			list2.remove(i);
		}
		System.out.println("역순 삭제 이후 list2 크기 : "+list2.size());
		//쉬는 시간 이후 자바 3교시부터
		//리스트 정렬
		
		
	}
}