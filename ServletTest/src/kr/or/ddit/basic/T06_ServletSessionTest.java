package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T06_ServletSessionTest extends HttpServlet {
/*
	세션(HttpSession)객체에 대하여...
	
	- 세션을 통해서 사용자(웹브라우저)별로 구분하여 정보를 관리할 수 있다.(세션ID 이용)
	- 쿠키를 사용할 때보다 보안이 향상된다. (사용자 정보가 서버에 저장되기 때문)
	
	- 세션 객체를 가져오는 방법
	HttpSession session = request.getSession(boolean값);
	boolean값 : true인 경우 => 세션 객체가 존재하지 않으면 새로 생성한다.
			  false인 경우 => 세션 객체가 존재하지 않으면 null을 리턴한다.
			  
	//그러면 누가 true, false를  리턴해준다는 건데, 누구의 값을 사용하는 걸까?		  
	
	
	- 세션 삭제 방법
	1. invalidate() 메서드 호출
	2. setMaxInactiveInterval(int interval)메서드 호출
	   => 일정시간(초)동안 요청이 없으면 세션 객체 삭제됨. 
	3. web.xml에 <session-config> 설정하기 (분 단위)
	
	//프로그래밍할 떄 처음 배우는 것
	1. 변수
	2. 범위(scope): 전역변수, 지역변수
		전역변수 - 편하다, 단점은? 관리를 잘해줘야 한다, 메모리라던가...
	
	우리가 지금까지 배웠던 객체가
	1. HttpServletRequest
	2. HttpServletResponse 
	
	지금 배울 것
	3. HttpSession
	
	지금까지 배운 Request, Response객체와 생명주기가 달라요.
	
	리퀘스트, 리스폰스 언제까지 살아있어요?
	브라우저를 쏴줄때까지
	이 이후에는 메모리에서 지워버려요
	이 다음에 요청하려면 새로운 객체를 만들어서 사용해야 해요
	
	* 세션은 언제까지 살아있어요?
	사용자와 서버가 연결되었다는 의미가 Session
	브라우저를 쏴줬어도, 그 이후에 Session이 살아 있을 수 있어요
	
	세션이 끊겼다가 새로 만들어지면 새로운 세션
	논리적인 연결을 관리해주는 개념
	
	같은 세션이에요?
	아까 연결된 같은 사람 = 동일한 세션
	
	동일한 세션 내에서는, 하나의 세션 객체가 만들어지고
	세션이 유지되는 순간은 살아있어요
	새로운 세션이 만들어졌다 그러면, 지금 만들어진 세션과 이전의 세션은 다른 세션이다.
	새로운 세션이 만들어졌다면, 동일한 세션(이전의 세션)에 접근 불가
	이 부분은 리퀘스트, 리스폰스과 동일하다. 
	
	이러한 세션 스코프에서 사용할 수 있는 객체인 HttpSession
	
	* 세션은 어떻게 관리?
	http는 비연결성
	세션은 세션Id라는 것을 사용, 난수값 같은 것을 생성해서
	브라우저한테 쿠키로 넘겨줘요
	setCookie()로 보내면 브라우저는 그걸 저장,
	그러면 기존의 쿠키처럼 sessionId가 계속 날라가겠죠.
	sessionId로 동일한 세션인지 아닌지 구분 가능
	
	서버쪽 메모리에 접근할 수 있는 방법을 만들어놨겠죠
	http세션을 서버쪽에 만들어서 필요한 정보에 접근
	
	사용자가 sessionId를 쿠키로 쏴주기 때문에,
	sessionId 목록에서 찾아보는 거죠, 해당 id가 있는지 없는지
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션을 가져오는데, 없으면 새로 생성한다.
		HttpSession session = req.getSession(true);
		
		// 생성시간 가져오기
		Date createTime = new Date(session.getCreationTime());
		
		// 마지막 접근시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "재방문을 환영합니다.";
		int visitCount = 0;		//방문횟수
		String userId = "sem";	//사용자 id
		
		if(session.isNew()) {	//세션이 새로 만들어졌는지 확인...
			title = "처음 방문을 환영합니다.";
			session.setAttribute("userId", userId);
		}else {
			visitCount = (Integer) session.getAttribute("visitCount");
			visitCount++;
			userId = (String) session.getAttribute("userId");
		}
		System.out.println("방문 횟수 : " + visitCount);
		session.setAttribute("visitCount", visitCount);
		
		
		//세션 삭제방법 3가지  line:28
//		세션 삭제 방법1)
//		session.invalidate();
		//세션을 무효화시키는 것, 날려버리는 것, 삭제하는 것
		//그러면 요청할 때마다? 메멘토 되는 거죠.
		//f5 누를 때마다 새로운 sessionId 발급되면서 새로운 사용자로 인식
		//언제 사용? 로그아웃
		
//		세션 삭제 방법2)
//		session.setMaxInactiveInterval(30);
		//단위: 초단위, 30초 내에 접근하지 않으면 세션 삭제하겠다
		//active: 활성화, inactive: 비활성화
		
//		세션 삭제 방법3)
//		web.xml에 <session-config> 설정하기 (분 단위)
		
		
		//응답 헤더에 인코딩 및 content type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>" + title + "</title></head>");
		out.println("<body>" + " <h1 align=\"center\">" + title + "</h1>"
					//타이틀을 2번이나 쓴다구? - 헤더의 tab부분이랑, 바디의 본문부분 ㅋㅋ 
					+ " <h2 align=\"center\">세션 정보</h2>"
					+ " <table border = \"1\" align=\"center\">"
					+ " <tr bgcolor=\"orange\">"
					+ " <th>구분</th><th>값</th></tr>"
					+ " <tr>"
					+ " <td>세션ID</td><td>" + session.getId() + "</td></tr>"
					+ " <td>생성시간</td><td>" + createTime + "</td></tr>"
					+ " <td>마지막 접근 시간</td><td>" + lastAccessTime + "</td></tr>"
					+ " <td>User Id</td><td>" + userId + "</td></tr>"
					+ " <td>방문 횟수</td><td>" + visitCount + "</td></tr>"
					+ "</table></body></html>");
		
					//JSP 사용하면, 이렇게 일일이 html 안 만들어줘도 된다
					//우리는 일단 JSP를 모른다는 가정 하에...
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}


