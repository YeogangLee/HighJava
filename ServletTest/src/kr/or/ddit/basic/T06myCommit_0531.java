package kr.or.ddit.basic;

public class T06myCommit_0531 {
/*

		//T05_ServletCookieTest에서 배운 기능
		1. 쿠키 생성 및 값 설정 Set (Update)
		2. 쿠키 읽기(조회) Read
		3. 쿠키 삭제 Delete(Remove)
		
		1. Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "utf-8"));
		   cookie.setMaxAge(60*60*24);
		   resp.addCookie(cookie);
		
		2. Cookie[] cookies = req.getCookies();
		   Cookie cookie = null;
		   cookie = cookies[i];
		   out.print("value : " + URLDecoder.decode(cookie.getValue(), "utf-8") + "<br>");
		
		3. Cookie cookie = null;
		   if((cookie.getName()).equals("userId")) {...}
		   cookie.setMaxAge(0);
		   resp.addCookie(cookie);
		
		- 쿠키 생성과 삭제가 동일한 로직으로 구현된다. ( Map의 값 추가put()와 수정처럼 put(같은키값, 수정할값) )
		
		getCookies()
		addCookie()
		
		- 쿠키의 생성 및 삭제 흐름
		1. 쿠키 객체 생성
		2. 쿠키 최대 지속시간 설정
		3. 응답 헤더에 쿠키 객체 추가
		

		//T06_ServletSessionTest
		세션 객체
		
		쿠키의 특징: 브라우저에 필요한 사용자 정보를 저장한다
		HttpSession객체: 필요한 정보를 세션에 저장한다, 객체로 저장한다 ==> 서버에 저장한다
		
		HttpServletRequest 객체와 HttpServletResponse객체와 다른 Life Cycle을 갖는다.
		리스폰스 하더라도 세션은 끝나지 않는다.
		세션이 끝나는 시기: 세션이 끝났을 때, 브라우저를 닫았을 때
		
		
		세션id는 세션 생성 후 쿠키에 저장된다
		-> 세션 종료 전까지(브라우저 닫기 전까지) 사용자를 구분할 수 있도록
		
		세션id 쿠키 이름: JSESSIONID
		
		- 특징
		Expires / Max-Age 값: Session
		=> 현재 세션까지 유효하다는 의미
		     세션id로 세션 구분, 세션 id: 16진수 32개 => 128비트
		
		세션 객체 생성 : req.getSession(true);
		
		객체로 새로 만들지 않고,
		기존의 세션에 접근: request객체.getSession(false)
		
		
		- 세션 삭제방법 3가지
		1. session.invalidate();
		   로그아웃 할 때 사용, 메멘토 되는 거에요 ㅋㅋ
		
		2. session.setMaxInactiveInterval(30); (초단위)
		
		3. web.xml에 세션 정보 등록(<session-config>, 분단위)
		   <session-config>
		     <session-timeout>1</session-timeout>
		   </session-config>

 */
}
