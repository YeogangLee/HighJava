package kr.or.ddit.basic;

/**
 * @author PC-14
 * 제너릭 클래스를 만드는 방법
 * 형식)
 * 				 //<제너릭타입글자>에는 아무 문자가 올 수 있다 - 이 아무 문자는, 이후에 타입을 정하는 문자로서 쓰이게 된다.
 * 				 //근데 아무 문자보다는, 밑에 적힌 T,K,V,E 문자를 사용하면 의미 전달이 더 잘 되겠죠.
	 * class 클래스명<제너릭타입글자> { <-- <제너릭타입글자>에 선언한 문자는 {} 내부에서 하나의 타입처럼 사용할 수 있다.
	 * 		제너릭타입글자 변수명; //변수 선언에 제너릭을 사용할 경우
	 * 		...
	 * 		제너릭타입글자 메서드명() { // 반환값이 있는 메서드에서 사용
	 * 			return 값;
	 * 		}
	 * 		...
	 * }
 * 
 * -- 제너릭 타입 글자 --
 * T ==> Type
 * K ==> Key
 * V ==> Value
 * E ==> Element(자료구조에 들어가는 요소들을 나타낼 때 사용)
 */

class NonGenericClass {
	//Object로 설정
	//장점 - 모든 걸 다 넣을 수 있다, 사용이 편함
	//단점 - 편한만큼 타입문제 발생 가능성 높음
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
}

//class MyGeneric<T, K, V> { //이렇게 여러개도 사용 가능
class MyGeneric<T> {
	//T - 지금은 무슨 타입인지 몰라도, 사용할 때 알려주겠다.
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
}


public class T02_GenericClassTest {
	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnNg1 = (String)ng1.getVal();
		System.out.println("문자열 반환값 rtnNg1 => " + rtnNg1);
		
		Integer irtnNg2 = (Integer)ng2.getVal();
		System.out.println("정수 반환값 irtnNg2 => " + irtnNg2);
		System.out.println();
		
		//사용할 시점에는 제너릭이 무슨 타입인지 정확히 알려준다.
		// - 그렇지 않으면 그냥 Object를 사용하는 것과 동일
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		//동일한 제너릭 클래스명에, 사용하는 시점에 원하는대로 제너릭 설정 가능
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getVal();
		irtnNg2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnNg1);
		System.out.println("제너릭 정수형 반환값 : " + irtnNg2);		
	}
}
