package kr.or.ddit.basic;

class Util {
	/**
	 * 제너릭 메서드 <T, R> R method(T t)
	 * : 파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메서드
	 * - 선언방법 : 리턴타입 앞에 <> 기호를 추가하고 타입 파라미터를 기술 후 사용한다.
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	//리턴타입 boolean, 파라미터는 2개 - Pair타입 p1, p2
	//제너릭 메서드 ? 꺽쇠<>가 반드시 return 타입 앞에 있어야 제너릭 메서드
//	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2){
// <K, V>를 없애면 Pair뒤에 매개변수에 빨간줄이 생기는데, 와일드카드 ?를 이용하면 오류X, ?의미: 타입 모른다
// 실행하다보면 오류가 생기긴 할 거다.
	public static boolean compare(Pair<?, ?> p1, Pair<?, ?> p2){
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare; //두 값이 true이면 true -> 같은 객체로 판단하는 메서드
	}
}
/**
 * 멀티타입<K, V>를 가지는 제너릭 클래스
 * @author PC-14
 *
 * @param <K>
 * @param <V>
 */
class Pair<K, V> {
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}//얘는 위의 클래스 선언부에서 선언한 V
	public void setValue(V value) {
		this.value = value;
	}
	
	//키와 값을 출력하기
//	public <M, N> void displayAll(M key, N val) {
	//좀 더 명확히 안 헷갈리게 하려면 M,N 사용하면 되겠죠.
	//일부러 여러분들 헷갈리게 하려고 똑같이 K, V 사용한 거다.
	public <K, V> void displayAll(K key, V val) {
		//위의 <K, V>는 여기 메서드 내부, scope는 여기 내부
		//근데 위의 Pair에서도 <K, V>를 사용
		//둘은 같을까? 다를까?
		//(K key, V val)의 K, V는 함수명에 있는 K, V를 지칭 - 지역변수처럼 한정되어 있다. 
		System.out.println(key.toString() + " : " + val.toString());
	
	}
	
}

public class T03_GenericMethodTest {
	public static void main(String[] args) {
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");
		
		//구체적 타입을 명시적으로 지정(생략가능)
		boolean result1 = Util.<Integer, String>compare(p1, p2);
		//compare는 제너릭 메서드이기 때문에 앞에 <>로 알려주기
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체가 아님");
		}
		
		Pair<String, String> p3 = new Pair<String, String>("001","홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002","홍길동");
		
		boolean result2 = Util.compare(p3, p4);
		//앞에 <>없애도 오류X, 위에서 파라미터인 p3, p4의 자료형(제너릭)을 명시했으므로 컴파일러가 유추할 수 있기 때문에
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체가 아님");
		}
		
		//제너릭 메서드 테스트
		p1.<String, Integer>displayAll("키", 180);
		//일부러 String, Integer 순서를 바꿈
		//제너릭 메서드가 아닌, 일반 메서드에서는 키,값 순서가 다르면 에러가 발생한다.
		//<>꺽쇠 지워도 오류X
		
	}
}
