package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T08_EqualsHashCodeTest {
/*
 * 해시함수는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
 * 해시 함수에 의해 얻어지는 값은 해시 값, 해시 코드, 해시 체크섬 또는 간단하게 해시라고 한다.
 * 
 * HashSet, HashMap, Hashtable과 같은 객체들을 사용하는 경우
 * 객체가 서로 같은지를 비교하기 위해 equals()메서드와 hashCode()메서드를 호출한다.
 * 그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야 한다.
 * HashSet, HashMap, Hashtable에서는 객체가 같은지 여부는 데이터를 추가할 때 검사한다.
 * 
 * - equals()메서드는 두 객체의 내용(값)이 같은지 비교하는 메서드이고,
 * - hashCode()메서드는 두 객체가 같은 객체인지 비교하는 메서드로 사용된다.
 * => 둘 다 모든 객체가 가지고 있는 메서드 
 *  
 * - equals()메서드와 hashCode()메서드에 관련된 규칙
 * 1. 두 객체가 같으면 반드시 같은 hashcode를 가져야 한다.
 * 2. 두 객체가 같으면 equals()메서드를 호출했을 때 true를 반환해야 한다.
 * 	    즉, 객체 a, b가 같다면 a.equals(b), b.equals(a) 둘 다 true이어야 한다.
 * 3. 두 객체의 hashcode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
 *    하지만, 두 객체가 같으면 반드시 hashcode 값이 같아야 한다.
 * 4. equals()메서드를 override하면 반드시 hashCode()메서드도 override해야 한다.
 * 5. hashCode()는 기본적으로 Heap에 있는 각 객체에 대한 메모리 주소 매핑 정보를 기반으로 한 정수값을 반환한다.
 *    그러므로, 클래스에서 hashCode()메서드를 override하지 않으면 절대로 두 객체가 같은 것으로 간주될 수 없다.
 *    
 * hashCode()메서드에 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대하여 같은 hashcode값을 만들어 낼 수 있다.
 * 그래서 객체가 같지 않더라도 hashcode가 같을 수 있다.
 */
	/**
	 * 
	 * 
	 */	
	
	public static void main(String[] args) {
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");
		
		//equals 오버라이드 이후 : true false, 이전 : false, false
		System.out.println("p1.equals(p2) : " + p1.equals(p2)); //equals 오버라이드 이후 - String처럼 비교된다, 안에 값이 같으니 같다.
		System.out.println("p1 == p2 : " + (p1==p2)); //메모리 상 다른 객체 
		
		Set<Person> set = new HashSet<>();
		
		set.add(p1);
		set.add(p2);
		//등록이 된 이유 : equals()만 override하고 hashCode()는 아직 override하지 않았기 때문
		//이런 이유로 equals()와 hashCode() 둘 다 override 해야 한다, 안전하게 둘 다 override
		System.out.println("p1, p2 등록 후 데이터");
		for(Person p : set) {
			System.out.println(p1.getId() + " : " + p.getName());
		}
		
		System.out.println("add(3) 성공여부 : " + set.add(p3));
		System.out.println("add(p3) 후 데이터");
		for(Person p : set) {
			System.out.println(p1.getId() + " : " + p.getName());
		}
		//p1, p2를 같은 값으로 처리했기 때문에, set에 p2가 없음에도 불구하고 p2를 성공적으로 삭제(p1이 삭제됨)
		System.out.println("remove(p2) 성공여부 : " + set.remove(p2));
		for(Person p : set) {
			System.out.println(p1.getId() + " : " + p.getName());
		}
		
		//set인데 왜 Interator를 안쓸까? set도 향상된 for문 사용이 가능하다
		//항상 그런 것은 아니고, 향상된 for문에 넣을 객체가 iterable 해야 사용이 가능하다.
		//HashSet은 iterable하다. - java doc 8
		
		
		
		
		
		
	}
}

class Person {
	private int id;
	private String name;
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	//String 비교할 때 쓰는 equals와 다른 메서드, override를 통해 다른 메서드가 됨
	public boolean equals(Object obj) {
		//p1 equals p2 일 때, this = p1, obj = p2
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			//정보를 담고 있는 class
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
