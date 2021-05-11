package kr.or.ddit.basic;
/*
 * 부모 클래스가 Serialiazble 인터페이스를 구현하고 있지 않을 경우
 * 부모객체의 필드값 처리 방법
 * 
 * 1. 부모 클래스가 Serializable 인터페이스를 구현하도록 해야 한다.
 * 2. 자식 클래스에 writeObject()와 readObject()메서드를 이용하여
 *    부모객체의 필드값(멤버변수)을 처리할 수 있도록 직접 구현한다.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 부모가 가진 인터페이스를 자식도 물려받는데...
 * 
 * 부모는 직렬화하지 않았기 때문에 write가 안된다
 * 부모를 왜 시리얼라이저블 인터페이스를 implements하지 않았을까
 * 일할때, 상위 클래스부터 먼저 정의해 나갈거에요
 * 상위 클래스에 변화가 생기면
 * 모든 하위 클래스에 변화가 생긴다
 * 
 * 부모가 시리얼라이저블 인터페이스를 임플리먼트하는 순간,
 * 자식 클래스는 원하든 원하지 않든 시리얼라이저블 인터페이스 타입이 된다
 * 그러면,
 * 부모는 시리얼라이저블 인터페이스가 아니면서
 * 자식은 시리얼라이저블 인터페이스 타입을 구현하고 싶다면?
 * 
 * 위에 적어둔 부모객체의 필드값을 처리하는 2가지 방법 이용 
 * 
 */
public class T16_NonSerializableParentTest {
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSerializableTest.bin");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child); //직렬화
		
		oos.close();
		
		FileInputStream fis = new FileInputStream("d:/D_Other/nonSerializableTest.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Child child2 = (Child) ois.readObject(); //역직렬화
		
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
	}
	
}
/**
 * Serializable 인터페이스를 구현하지 않은 부모 클래스
 * @author PC-14
 *
 */
class Parent {
//class Parent implements Serializable { //부모가 시리얼라이저블 인터페이스를 구현하면 문제X지만, 이런 경우는 제외하고 ...
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}	
}
/**
 * Serializable 인터페이스를 구현한 자식 클래스
 * @author PC-14
 *
 */
class Child extends Parent implements Serializable {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
	 * 직렬화 될 때 자동으로 호출됨.
	 * (접근 제한자가 private이 아니면 자동호출되지 않음)
	 * @param out
	 * @throws IOException
	 */
	//접근제어자는 private으로 해야 한다.
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(getParentName());
		out.defaultWriteObject(); //원래 기능, 우리가 필요로 하는 작업: 부모의 필드셋도 자동으로 처리 -> 위의 코드 이용
	}
	
	/**
	 * 역직렬화 될 때 자동으로 호출됨
	 * (접근 제한자가 private이어야 자동 호출 된다.)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		setParentName(in.readUTF());
		in.defaultReadObject();
	}
	
	//java IO 끝	
}
