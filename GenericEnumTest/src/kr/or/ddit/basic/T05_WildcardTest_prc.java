package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T05_WildcardTest_prc {
	public static void main(String[] args) {
		ObstKiste<Obst> obstKiste = new ObstKiste<>();
		ObstKiste<Apfel> apfelKiste = new ObstKiste<>();
		ObstKiste<? extends Obst> obstKiste2 = new ObstKiste<Obst>();
		
		obstKiste.add(new Apfel());
		obstKiste.add(new Traube());
		
		apfelKiste.add(new Apfel());
//		apfelKiste.add(new Traube());
		
		Saft.makeJuice(obstKiste);
		Saft.makeJuice(apfelKiste);
	}
}

/**
 * 과일 주스기
 * @author PC-14
 *
 */

class Saft {
	static void makeJuice(ObstKiste<? extends Obst> kiste) {
		String fruitListStr = null;
		
		int cnt = 0;
		for(Obst o : kiste.getFruitList()) {
			if(cnt==0) {
				fruitListStr += o;
			}else {
				fruitListStr +=", " + o;
			}
			cnt++;
		}
		System.out.println(fruitListStr + " => 주스 완성 !");
	}
}

/**
 * 과일
 * @author PC-14
 *
 */

class Obst {
	private String name; //과일 이름
	
	public Obst(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//toString 오버라이드 안해봐야지
	
}


class Apfel extends Obst {

	public Apfel() {
		super("사과");
		
	}
		
}

class Traube extends Obst {

	public Traube() {
		super("포도");
	}
		
}

/**
 * 과일 상자
 * @param <T>
 */
class ObstKiste<T> {
	private List<T> fruitList;
	
	public ObstKiste() {
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
