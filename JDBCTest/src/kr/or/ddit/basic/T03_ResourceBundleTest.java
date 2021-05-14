package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class T03_ResourceBundleTest {
/**
 * 	ResourceBundle 객체
 *  => 확장자가 properties인 파일 정보를 읽어와, key값과 value값을 분리한 정보를 갖는 객체
 *     읽어올 파일은 'key값=value값'형태로 되어 있어야 한다.
 */
	public static void main(String[] args) {
		
		//ResourceBundle 객체를 이용하여 파일 읽어오기

		System.out.println("기본 로케일 정보 : " + Locale.getDefault());
		//ko_KR, korean_Korea
		
		//ResourceBundle 객체 생성하기
		//=> 파일을 지정할 때는 '파일명'만 지정, 확장자는 지정X
		//   (이유: 확장자는 항상 properties이기 때문에)
//		ResourceBundle bundle = ResourceBundle.getBundle("db"); //"db.properties"라고 하지 않는다.
//		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.JAPAN);  //곤니찌와
		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.GERMAN); //Hallo
		
		//key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		//key값 개수만큼 반복해서 value값 가져오기
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			//key값을 이용하여 value값을 읽어오는 방법
			//=> bundle객체변수.getString(key값);
			String value = bundle.getString(key); //ResourceBundle 객체는 getString()
												  //Properties 객체는 getProperty()
			
			System.out.println(key + " => " + value);
		}
		System.out.println("출력 끝...");
		
		
		//리소스 번들
		/*
		 * internationalization 국제화
		 * 용도로 리소스 번들 객체 사용 가능
		 * 미국인이 쓸 때는 영어로, 일본인이 쓸 때는 일본어로
		 * 프로그램의 메세지가 출력될 수 있게
		 * 
		 * 나라별로 사용하는 언어, 통화(지폐), 날짜, 시간
		 * locale 정보 - 나라마다 바뀌는 정보
		 * locale 정보 확인 방법
		 * 
		 * Locale 클래스
		 * res 소스폴더의 properties파일을 읽어온다
		 * 파일명도 신경 써서 지어줘야 한다
		 * 한국어는 db_ko로 지정, 독일어는 db_de로 지정
		 * 만약 파일명으로 지정해둔 값이 Locale에 없으면
		 * 디폴트 properties파일을 읽어온다
		 * 아무것도 붙지 않은 db.properties파일
		 * 
		 */
	}
}
