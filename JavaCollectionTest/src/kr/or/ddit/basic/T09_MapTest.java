package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T09_MapTest {
/*
 * Map
 * - key값과 value값을 한 쌍으로 관리하는 객체
 * - key값은 중복을 허용하지 않고 순서가 없다.(Set의 특징)
 * - value값은 중복을 허용한다.(List의 특징)
 */
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		
		//자료 추가 : put(key값, value값);
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map : "+map);
		
		//자료 수정 : put(수정할 데이터의 key값, 새로운 value값) - 추가도 put, 수정도 put
		//		     데이터 저장할 때 key값이 같으면 나중에 입력한 값이 저장된다.
		map.put("addr", "서울");
		System.out.println("map : " +map);
		
		//자료 삭제 : remove(삭제할 key값);
		map.remove("name");
		System.out.println("map : " + map);
		
		//자료 읽기 : get(key값); - list는 파라미터로 index, map은 key
		System.out.println("addr = " + map.get("addr"));
		System.out.println("=========================");
		
		/*
		 * key값들을 읽어와 자료를 출력하는 방법
		 * 
		 * 방법1. keySet() 메서드 이용
		 * - Map의 key값들만 읽어와 Set형으로 반환
		 * 
		 * 방법2. Set형의 데이터를 '향상된 for문'으로 처리
		 * - Iterator를 사용하지 않아도 된다.
		 * 
		 * 방법3. value값만 읽어와 출력하기
		 * 
		 * 방법4. Map에는 Entry라는 내부 클래스가 만들어져 있다.
		 * - Entry클래스는 key와 value라는 멤버변수로 구성되어 있다.
		 * - Map에서 이 Entry클래스들을 Set형식으로 저장하여 관리한다.
		 * 
		 * Entry 객체 전체를 가져오기(가져온 Entry들은 Set형식으로 되어 있다.)
		 * - entrySet()메서드를 이용하여 가져온다.
		 */
		
		//방법1.
		Set<String> keySet = map.keySet(); //HashMap객체의 메서드명 그대로 keySet을 전달하는 메서드
		
		System.out.println("1.Iterator를 이용한 방법 + keySet");
		
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("----------------------------");
		
		//방법2.
		System.out.println("2.향상된 for문 이용 + keySet");
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("----------------------------");
		
		//방법3. value값만 읽어와 출력하기
		System.out.println("3.values()메서드 이용한 방법");
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("----------------------------");
		
		//방법4. 가장 Map스럽게 꺼내기 - key, value 둘 다 쓸거니, Entry로 둘 다 들고올래
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		//Entry라는 내부 인터페이스, Map도 인터페이스
		//왜 굳이 내부로? 별도로 만들어 놓지.
		//장점 - 객체를 안 만들어도된다? X 관련이 없어요, 일반적으로 클래스는 다 Static
		//장점 - 클래스를 생성하는 이유는, 객체를 만들기 위해 클래스를 생성하는 거죠, 객체를 찍어내기 위해
		//근데 이걸 외부에 만들어놓으면 다른 클래스들도 쓸 수 있죠
		//나만 쓰려면 내부에 만들어 놓으면 돼요. 그러면 외부에 유출X
		//따라서 Entry인터페이스는 Map이랑만 관련이 있는 거에요.
		
		//f3으로 확인해보면, interface Entry<K,V> 내부에 getKey()와 getValue()메서드를 가지고 있는 것을 볼 수 있다.
		//interface Entry<K,V> 상위에는 public interface Map<K,V>가 정의되어 있다.
		
		//가져온 Entry객체들을 순서대로 처리하기 위해서 Iterator객체로 변환
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		System.out.println("4.Map.Entry<String, String> 이용한 방법");
		while(entryIt.hasNext()) {
			//Map인터페이스 내부의 Entry인터페이스
			Map.Entry<String, String> entry = entryIt.next();
			
			//f3으로 확인해보면, interface Entry<K,V> 내부에 getKey()와 getValue()메서드를 가지고 있는 것을 볼 수 있다.
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}
		
		//여기까지 HashMap의 CRUD	
		
	}
}
