package kr.or.ddit.basic;

class Util_prc {
	public static <K, V> boolean compare(Pair2<K, V> p1, Pair2<K, V> p2) {
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare;
	}
}

class Pair2<K, V> {
	private K key;
	private V value;
	
	public Pair2(K key, V value){
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
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	public <K, V> void displayAll(K key, V val) {
		System.out.println(key.toString() + " : " + val.toString());
	}
	//위 아래 메서드는 동일한 메서드, class명에 있는 Pair2<K, V>와 여기의 <K, V>는 상관X
	//그래서(상관이 없어서), <V, K> 형태로 사용하더라도 상관X인듯
//	public <M, N> void displayAll(M key, N val) {
//		System.out.println(key.toString() + " : " + val.toString());
//	}
	
}

public class T03_GenericMethodTest_prc {
	public static void main(String[] args) {
		Pair2<Integer, String> p1 = new Pair2<Integer, String>(1, "홍길동");
		Pair2<Integer, String> p2 = new Pair2<Integer, String>(1, "홍길동");
		
		boolean result1 = Util_prc.<Integer, String>compare(p1, p2);
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체");
		}else {
			System.out.println("논리(의미)적으로 다른 객체");
		}
		
		Pair2<String, String> p3 = new Pair2<String, String>("001","홍길동");
		Pair2<String, String> p4 = new Pair2<String, String>("002","홍길동");
		
		boolean result2 = Util_prc.compare(p3, p4);
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체");
		}else {
			System.out.println("논리(의미)적으로 다른 객체");
		}
		
		p1.displayAll("키", 180);
		
		
	}
}
