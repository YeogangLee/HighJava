package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class T02_PropertiesTest {
	//외부의 properties파일을 읽어와 Properties 객체로 처리하기
	public static void main(String[] args) {
		//읽어온 정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		
		//읽어올 파일명을 이용한 File 객체 생성
		File file = new File("res/db.properties");
		
		try {
			//파일 읽기를 수행할 FileInputStream 생성
			FileInputStream fis = new FileInputStream(file); //생성자에 String 바로 넣어도 된다.
			
			//Properties 객체로 파일 내용 읽기
			prop.load(fis); //파일 내용을 읽어와 key, value 값으로 분류 후 Properties 객체에 담아준다
//			prop.load();    //fis말고도 Reader도 올 수 있다, 파라미터 자리인 괄호 안에서 ctrl+space로 확인 가능
			
			//읽어온 자료 출력하기
			
			//key값만 읽어와 Enumeration 객체로 변환하기
			Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
			//이터레이터 등장 전에 사용하던 방식, 사용법도 비슷하다
						
			//key값 개수만큼 반복해서 값 출력하기
			
			//keys.hasMoreElements()
			//=> 다음  포인터 위치에 자료가 있으면 true, 없으면 false
			while(keys.hasMoreElements()) { //이터레이터에서의 hasNext
				String key = keys.nextElement();	//이터레이터에서의 next
				String value = prop.getProperty(key); //값 들고 올 때는 getProperty, 반대는 setProperty
				System.out.println(key + " => " + value);
			}
			//지금 자바소스코드내의 정보들을 properties파일로 뽑아내려 함
			//왜?
			//어떤 장점?
			//자바 소스 내에 하드코딩한 값들이 많다는 것은, 나중에 수정을 해야 할 때 ...
			//수정을 위해서 필요한 작업: 컴파일, 컴파일 된다 -> 클래스가 바뀐다
			//서버에 있는 프로그램을 들고왔다면, 서버의 소스가 바뀌고, 그 서버에 바뀐 소스를 업로드 해야 하고 ...
			
			System.out.println("출력 끝...");
			
		}catch(IOException ex) {
			ex.printStackTrace();			
		}
	}
}
