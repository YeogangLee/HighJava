package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T05_WildcardTest {
	/*
	 * 와일드카드 선언 방법
	 * 
	 * <? extends T> : 와일드카드의 상한 제한. T와 그 자손들만 가능
	 * <? super T>   : 와일드카드의 하한 제한. T와 그 조상들만 가능 //?(Object 제외)
	 * <?>			 : 모든 타입이 가능 <? extends Object>와 동일
	 * 
	 * 사용이유 : 
	 * 사용 목적들을 생각하며 복습, 공부해라
	 * 
	 */
	
	public static void main(String[] args) {
		
		FruitBox<Fruit> fruitBox = new FruitBox<>(); //과일상자
		FruitBox<Apple> appleBox = new FruitBox<>(); //사과상자
		FruitBox<? extends Fruit> fruitBox2 = new FruitBox<Fruit>();
				
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
//		appleBox.add(new Grape()); //사과 상자에 포도 담기 불가능, 에러
		
		Juicer.makeJuice(fruitBox); //성공
//		Juicer.makeJuice(appleBox); //makeJuic()메서드는 Fruit만 받으므로 X 
		Juicer.makeJuice(appleBox); //Fruit -> T 로 변경후 문제X 
		
	}
}

class Juicer {
//	static void makeJuice(FruitBox<Fruit> box) {
//	static <T extends Fruit> void makeJuice(FruitBox<T> box) { //모든 과일을 넣을 수 있도록 수정
	//T에서 extends Fruit를 적는 순간, Fruit와 Apple, Grape만 올 수 있다.
	static void makeJuice(FruitBox<? extends Fruit> box) {
		String fruitListStr = ""; // 과일 목록
		
		int cnt = 0;
//		for(Fruit f : box.getFruitList()) {
//		for(T f : box.getFruitList()) {
		for(Fruit f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		System.out.println(fruitListStr + " => 주스 완성!");
	}
}

/**
 * 과일
 * @author PC-14
 *
 */

class Fruit {
	private String name; //과일 이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//toString Override 안 해주면 이상하게 뜬다.
	@Override
	public String toString() {
		return "과일("+name+")";
	}
}


class Apple extends Fruit {
	public Apple() {
		super("사과");
	}	
}

class Grape extends Fruit {
	public Grape() {
		super("포도");
	}	
}

/**
 * 과일 상자
 * @param <T>
 */
class FruitBox<T> {
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<T>();
	}
	
	public List<T> getFruitList() {
		return fruitList;
	}
	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}
