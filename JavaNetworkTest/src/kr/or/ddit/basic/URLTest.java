package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {
/*
 * URL 클래스 => 인터네에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스
 * 
 * url : 리소스에 대한 위치 정보, 사용 이유: url을 알면 자원에 접근할 수 있다.
 */
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		URL url = new URL("http", "ddit.or.kr", 80, "/main/index.html?ttt=123#kkk");	
		
//		url = new url //생성자가 5,6개
		
		System.out.println("전체 URL 주소 : " + url.toString());
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("query : " + url.getQuery()); //쿼리 정보
		System.out.println("file : " + url.getFile()); //쿼리 정보 포함
		System.out.println("path : " + url.getPath()); //쿼리 정보 미포함
		System.out.println("port : " + url.getPort());
		System.out.println("ref : " + url.getRef());
		System.out.println();
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString());
			
	}
}
