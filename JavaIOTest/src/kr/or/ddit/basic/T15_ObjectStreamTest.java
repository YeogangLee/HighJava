package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 객체 입출력 보조 스트림 예제(직렬화와 역직렬화)
 * @author PC-14
 *
 */
public class T15_ObjectStreamTest {
	public static void main(String[] args) {
		//Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "대구");
		Member mem3 = new Member("이몽룡", 40, "광주");
		Member mem4 = new Member("성춘향", 50, "부산");
		
		try {
			//객체를 파일에 저장하기
			
			//출력용 스트림 객체 생성
			ObjectOutputStream oos 
//			= new ObjectOutputStream(new FileOutputStream("d:/D_Other/memObj.bin"));
			
			//효율적인 수행을 위해 버퍼드 스트림 추가
			= new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("d:/D_Other/memObj.bin")));
			//오브젝트를 저장하기 위해, Object 인풋이 아니라 Output Stream
			//파일에 저장할 거라서, FileOutputStream
			//바이트 기반으로 저장할 때는, FileOutputStream
			
			//쓰기 작업
			oos.writeObject(mem1); //직렬화
			oos.writeObject(mem2); //직렬화
			oos.writeObject(mem3); //직렬화
			oos.writeObject(mem4); //직렬화
			//오브젝트 종류 상관없이 write할 객체를 넣어준다. ex. mem1, mem2, ...
			//직렬화 이후 메모리에 있는 직렬화된 데이터를 1바이트씩 읽어와 디스크에 저장하는 과정
			//직렬화 이후에 1바이트씩 읽는 게 가능하다
			//역직렬화: 거꾸로, 1바이트씩 데이터 전달해서, heap메모리에 객체 생성
			
			System.out.println("쓰기 작업 완료");
			oos.close();
			
			//=======================================
			
			//저장한 객체를 읽어와 출력하기
			
			//입력용 스트림 객체 생성			
			ObjectInputStream ois = 
//					new ObjectInputStream(new FileInputStream("d:/D_Other/memObj.bin"));
					
					//버퍼드 스트림(보조 스트림) 추가
					new ObjectInputStream(
							new BufferedInputStream(new FileInputStream("d:/D_Other/memObj.bin")));
			
			//위에서 방금 저장한 파일에서 읽어오기
			//이번에는 거꾸로, 오브젝트 단위로, 오브젝트를 읽어오기
			
			Object obj = null;
			//모든 객체를 읽을 때 적용시키기 위해, Object 클래스 사용
			
			while((obj = ois.readObject()) != null) {
				//읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
				Member mem = (Member) obj;
				
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("----------------------");
			}
			
			ois.close();
		
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			
		}catch(IOException ex) {
			//더 이상 읽어올 객체가 없으면 예외 발생함.
			ex.printStackTrace();
			System.out.println("출력 작업 끝...");
			//만약 이게 실제 앱이라면,
			//예외 출력 코드를 주석처리하면, 실행에는 문제가 없으므로, 정상적으로 수행된 것처럼 보인다.
			
			//코드를 실행하면 EOFException(End Of File Exception) 예외 에러가 출력되는데,
			//이 에러가 발생하면 정상적으로 파일을 끝까지 읽어온 것
			//EOFException: IOException의 일종, IOException이 더 상위 예외
			
		}
		
	}
}

class Member implements Serializable {
	/*
	 * 자바는 Serializable 인터페이스를
	 * 구현한 클래스만 직렬화 할 수 있도록 제한하고 있다.
	 * 
	 * 구현하지 않으면 직렬화 작업시에,
	 * java.io.NotSerializableException 예외 발생함
	 */
	
	/*
	 * transient: 직렬화 대상에서 제외하는 키워드 
	 * 
	 * a.일시적인, 지속 가능하지 않은
	 * transient => 직렬화가 되지 않을 멤버변수에 지정한다
	 *              (*static 필드도 직렬화 대상이 아니다,
	 *              static변수들은 객체 생성X이기 때문에 직렬화 대상X)
	 * 직렬화가 되지 않은 멤버변수는 기본값으로 저장된다
	 * (참조형 변수: null, 숫자형 변수: 0)
	 * 
	 */
	
	/*
	 * 직렬화를 해도 되는지 안해도되는지 어떻게 판단하냐면
	 * Serializable 인터페이스 구현 여부에 따라.
	 * 
	 * 추상 메서드가 없는 인터페이스(태그 인터페이스)를 왜 만들었을까?
	 * -> 타입을 보고 직렬화 체크를 위해, 표시를 위해
	 * 인터페이스를 통해,
	 * Member타입도 되지만 Serializable 타입도 되는 것
	 * 타입이 중요해요, 타입이 뭔지냐에 따라 쓸 수 있는 메서드들도 달라지게 되는 거고...
	 */
	
//	private String name;
	private transient String name;	//참조형 변수: null
//	private int age;
	private transient int age;		//숫자형 변수: 0
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}	
}
