package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
												//Malformed - 형태에 문제가 있는, Wellformed - 형태가 잘 갖추어진
	public static void main(String[] args) throws IOException {
		//URLConnection => 애플리케이션 URL 간의 통신 연결을 위한 추상 클래스
		
		// 특정 서버(예:naver서버)의 정보와 파일 내용을 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
//		URL url = new URL("https://naver.com/");
		
		/* Header 정보 가져오기 */
		
		//URLConnection 객체 구하기
		URLConnection urlConn = url.openConnection(); //기본값이 http프로토콜 연결	
		//클래스 블럭선택 + ctrl T : 하이라키
		
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("Content : " + urlConn.getContent());
		//sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@6276ae34
		//HttpInputStream객체가 넘어왔다는 것, @6276ae34 메모리 주소?
		
		System.out.println();
		
		//전체 Header 정보 출력
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		//우리가 기본적으로 사용하는 프로토콜 http
		//
		
		//Header의 key값 구하기
		Iterator<String> iterator = headerMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key= iterator.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("----------------------------------");
		
		/* 해당 호스트의 페이지 내용 가져오기 */
		
		//파일을 읽어오기 위한 스트림 생성
//		InputStream is = url.openConnection().getInputStream();
		InputStream is = url.openStream(); // 이렇게도 가능
		
		//문자를 안 깨지게 읽으려면 필요한 InputStreamReader
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		
		int c = 0;
		while((c=isr.read()) != -1) {
			System.out.print((char)c);
		}
		
		//스트림 닫기
		isr.close();
		
		/*
		 * 콘솔창 출력양 늘리기
		 * 콘솔창 > 우클릭 > Preferences > Console buffer size 텍스트박스, 원하는 값 입력
		 */
		
		/*
		    <div>
			<a href="https://finance.naver.com/world/sise.nhn?symbol=XTR@DAX30" class="card_stock " data-clk="squ.ger">
			<strong class="stock_title">증시</strong>
			<div class="stock_box">
			<em class="name">독일</em>
			<strong class="current">15,411.03</strong>
			<span class="rate rate_up">26.95 +0.18%</span>
			</div>
			</a>
			</div> 
		 */
	}
}


















