package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T05_ServletCookieTest extends HttpServlet {
/**
	쿠키 정보를 다루기 위한 예제
	(웹 서버와 브라우저는 애플리케이션을 사용하는 동안
	  필요한 값을 쿠키를 통해 공유하여 상태를 유지한다.)
 
 	1. 구성요소
 	- 이름
 	- 값
 	- => (이름, 값) ex. 이름: 홍길동 - 이름:이름, 값:홍길동
 	
 	- 유효시간(초) : 유효시간이 지나면 브라우저에서 데이터 삭제, 시간이 지나기 전까지만 의미가 있다
 	- 도메인: ex) www.somehost.com, .somehost.com
 			 => 쿠키의 도메인이 쿠키를 생성한 서버의 도메인을 벗어나면
 			        브라우저는 쿠키를 저장(생성)하지 않는다.
 			        
 	//다른 서버 쿠키를 들고오면 보안 이슈 발생할 수 있다, 그래서 브라우저 자체에서 그런 기능을 막아놓은 것

 	- 경로: 쿠키를 공유할 기준 경로를 지정한다.(도메인 이후 부분으로 디렉토리 수준)
 		   => 지정하지 않으면 실행한 URL의 경로부분이 사용됨.
 		   
 	//ContextPath 또는 Context Root가 나와서 쭉 타고 들어가는 것
 	 * 소은씨 정보를 저장해두면, 네이버닷컴에 소은씨를 보고 정보를 쿠키 만들어서 저장해놓으면,
 	 * 저장이 된다. 본인은 모르고, 로그인을 하면 헤더에 쿠키 정보를 같이 보내준다
 	 * 필요시에 파싱해서 쓰면 되고, 필요없으면 안 써도 된다. 헤더에 계속 보내주는 것
 	 * 
 	 * 기본값이 루트라서 항상 쿠키가 날라간다, 그런데 쿠키를 세팅하고 싶으면 이걸 이용해서 경로를 지정할 수 있다 
 	 * ex. user/logging 이렇게 경로를 설정해두면 여기에서만 쿠키를 사용
 		   
 	2. 동작방식
 	- 쿠키 생성 단계: 생성한 쿠키를 응답 데이터의 헤더에 저장하여 웹 브라우저에 저장한다.
 	- 쿠키 저장 단계: 웹 브라우저는 응답 데이터에 포함된 쿠키를 쿠키저장소에 보관한다.
 	- 쿠키 전송 단계: 웹 브라우저는 저장한 쿠키를 요청이 있을 때마다 웹 서버에 전송한다.
 				  (삭제되기 전까지...)
 				    웹 서버는 브라우저가 전송한 쿠키를 사용해서 필요한 작업을 수행한다.
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		setCookieExam(req, resp); // 쿠키 설정 예제	
		
//		readCookieExam(req, resp); // 쿠키 정보 읽기 예제
		
//		deleteCookieExam(req, resp); // 쿠키 정보 삭제 예제
		
	
	}
	
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	/*
	 * 사용중인 쿠키 정보를 삭제하는 방법...
	 * 
	 * 1. 사용중인 쿠키 정보를 이용하여 쿠키 객체를 생성한다.
	 * 2. 쿠키 객체의 최대 지속시간을 0으로 설정한다.
	 * 3. 설정한 쿠키 객체를 응답 헤더ResponseHeader에 추가하여 전송한다.
	 * 
	 * 삭제를 하는데 새로운 쿠키 객체를 생성한다?
	 * 그걸 응답헤더에 보내준다면, 그러면 원래 쿠키 객체는?
	 * =>
	 * request객체가 정보를 받아오는 곳은 웹 브라우저,
	 * response객체가 정보를 보내주는 곳도 웹 브라우저
	 * 
	 * 삭제할 쿠키와, 같은 이름의 쿠키 객체를 만든 후,
	 * 지속시간을 0으로 set하면 해당 쿠키의 지속시간은 과거로 맞춰지고,
	 * (유닉스 시간(Unix time / Unix epoch): 00:00:00 UTC on 1 January 1970)
	 * 브라우저가 Expires/Max-Age가 과거인, 해당 쿠키를 삭제하게 된다.
	 */
		
		Cookie[] cookies = req.getCookies();
		Cookie cookie = null;
		
		//응답 헤더에 인코딩 및 Content Type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키 정보 삭제 예제";
		
		out.println("<html><head><title>" + title + "</title>");
		out.println("<body>");
		
		if(cookies != null) {
			out.println("<h2>" + title + "</h2>");
			
			for(int i=0; i<cookies.length; i++) {
				cookie = cookies[i];
				
				if((cookie.getName()).equals("userId")) {
					//쿠키 제거하기
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					//addCookie()사용: 그래야 나중에 Request Header에 Set-Cookie라는 헤더가 추가된다.
					out.println("삭제한 쿠키: " + cookie.getName() + "<br>");
				}
				
				out.print("쿠키 이름: " + cookie.getName() + ", ");
				out.print("쿠키 값   : " + URLDecoder.decode(cookie.getValue(), "utf-8")+ "<br>");							
			}//for문
			
		}else {
			out.print("<h2>등록된 쿠키 정보가 없습니다.</h2>");
		}
		
		out.println("</body>");
		out.println("</html>");			
	}

	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//현재 도메인에서 사용 중인 쿠키 정보 배열 가져오기
		Cookie[] cookies = req.getCookies();
		Cookie cookie = null;
		//쿠키가 여러 개 일수 있어서 배열로 들고 온다
		//쿠키 넣어주는 건 누가 해요? 톰캣이
		//톰캣이 http서블릿리퀘스트, http서블릿리스폰스 생성해주죠.

		//우리는 객체 2개 핸들링만 하면 돼요,
		//리퀘스트 객체HttpServletRequest에서 getCookies()메서드만 호출하면 돼요
		
		//쿠키는 어디에 저장? 브라우저
		//브라우저에 있는 쿠키를 삭제하고 싶을 때: 삭제 메서드 호출
		
		//응답 헤더에 인코딩 및 Content Type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
	
		PrintWriter out = resp.getWriter();
		String title = "쿠키 정보 읽기 예제";
		
		out.println("<html><head><title>" + title + "</title></head>");
		out.print("<body>");
		
		if(cookies != null) {
			out.println("<h2>" + title + "</h2>");
			
			for(int i=0; i<cookies.length; i++) {
				cookie = cookies[i];
				out.print("name : " + cookie.getName() + "<br>");
				out.print("value : " + URLDecoder.decode(cookie.getValue(),"utf-8")+ "<br>");				
				out.print("domain : " + cookie.getDomain() + "<br>");
				out.print("path : " + cookie.getPath() + "<br>");
				out.print("MaxAge(초) : " + cookie.getMaxAge() + "<br>");
				out.print("<hr>");				
			}
			
		}else {
			out.println("<h2>등록된 쿠키 정보가 없습니다.</h2>");
		}
				
//		out.print("</body></html>"); 왜 나눠 쓰셨지
		out.print("</body>");
		out.print("</html>");	
	}

	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	/*
		쿠키 정보를 설정하는 방법...
		
		1. 쿠키 객체를 생성한다. 사용불가문자(공백, []()=,"/?@:;)
		Cookie cookie = new Cookie("키값", "value값");
		쿠키값은 사용불가문자를 제외한 나머지 출력 가능한 아스키 문자 사용 가능함.
		=> 이외의 값(예를 들면 한글)을 사용시에는 URLEncoder.encode() 사용하여 인코딩 처리를 해준다.
		
		//URL인코딩 = 퍼센트 인코딩, 인코딩 처리 이후 모습을 가리킨다
		//한글로 복원하려면 디코딩 해줘야 하고, 인코딩처럼 마찬가지로 URLDecoder에 decode()명령이 있다, 그걸 사용
		//경로, 도메인 정보: 설정 안하면 기본값으로 설정 경로는 Context Root
		
		
		2. 쿠키 최대 지속시간을 설정한다.(초단위)
		=> 지정하지 않으면 웹 브라우저를 종료할 때 쿠키를 함께 삭제한다.
		cookie.setMaxAge(60*60*24); //24시간: ((60초 * 60)분 *24)시간, 1일
		
		3. 응답 헤더에 쿠키 객체를 추가한다.
		response.addCookie(cookie);
		=> 출력 버퍼가 플러시된 이후에는 쿠키를 추가할 수 없다.
		   (응답 헤더를 통해서 웹 브라우저에 전달하기 때문에...)
	 */
	
		//쿠키 생성하기
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
//		System.out.println("req.getParameter('userId') : " +  req.getParameter("userId"));
		
		//쿠키 값에 한글을 사용시 인코딩 처리를 해준다.
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "utf-8"));
		
		// 쿠키 소멸 시간 설정(초단위)
		userId.setMaxAge(60 * 60 * 24); //1일
		userId.setHttpOnly(true);		//javascript를 이용한 직접 접근 방지
		
		name.setMaxAge(60 * 60 * 48);	//2일
		
//		2021-06-01T05:40:01.578Z
//		2021-06-02T05:40:01.578Z
		/*
		 * 유효기간을 각각 1일, 2일로 줘서, 원래는 6월 2일 14시 40분이 되어야 하는데
		 * 기준이 그리니치 세계 표준시로 설정되어서, 9시간 이르게 저장된다. (한국은 표준시 + 9시)
		 */
		
		/*
		 * Set-Cookie: userId=a001; Expires=Tue, 01-Jun-2021 06:03:20 GMT; HttpOnly
		 * Set-Cookie: name=%ED%99%8D%EA%B8%B8%EB%8F%99; Expires=Wed, 02-Jun-2021 06:03:20 GMT
		 */
		
		//응답 헤더에 쿠키 추가하기
		resp.addCookie(userId);
		resp.addCookie(name);
		
		//요기까지 하면 헤더 세팅 끝인데,
		//사용자에게 잘 보여주기 위해 밑에 코드 추가, html을 만들어서 브라우저에 쏴주고 있다.
		
		
		//응답 헤더에 인코딩 및 Content-Type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키 설정 예제";
		
		out.println("<html><head><title>" + title + "</title></head>");
		out.println("<body>" + "<h1 align=\"center\">" + title + "</h1>");
		out.print("<li><b>ID</b>: " + req.getParameter("userId") + "</li>");
		out.println("<li><b>이름</b>: " + req.getParameter("name") + "</li>");
		out.println("</body></html>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
