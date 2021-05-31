package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02_ServletTest extends HttpServlet {
/*
 * 서블릿 동작 방식에 대하여...
 * 
 * 1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request를 Servlet Container로 전송(요청)한다.
 * 
 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리할지를 검색한다.
 *    (로딩이 안 된 경우에는 로딩함. 로딩시 init()메서드 호출됨.)
 *    //컨테이너 - 톰캣, 서블릿 컨테이너, WAS와스
 * 
 * 3. Servlet Container는 요청을 처리할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.
 *    (호출시 HttpServletRequest 및  HttpServletResponse객체를 생성하여 넘겨준다)
 * 
 * 4. service() 메서드는 메서드 타입(요청)을 체크하여 적절한 메서드를 호출한다.
 * 	  (doGet(), doPost(), doPut(), doDelete() 등...)
 * 	  //지금은 doGet(), doPost()에만 집중, 나중에 Spring의 api를 사용하면서 나머지 메서드들을 쓸 일이 있을 거다 ...
 * 
 * 5. 요청 처리가 완료되면, HttpServletRequest 및 HttpServletResponse 객체는 소멸된다.
 * 
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에 destroy()메서드가 호출된다.
 * 
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post방식으로 넘어오는 Body데이터를 인코딩 처리함.
		// GET방식은 톰캣은 URLEncoding 설정을 이용함.
		// 반드시 request에서 값을 가져오기 전에 먼저 설정해야 적용된다.
		req.setCharacterEncoding("UTF-8");
		
		//우리는 한글을 사용하기 때문에 항상 인코딩을 고민해야 한다
		//밑에서 getParameter()로 값을 꺼내기 전에 인코딩
		
		//요청 정보로부터 name값을 가져옴
		String name = req.getParameter("name");
		
		//꺼낸 데이터가 영어면 상관이 없는데,
		//일반적인 아스키 문자가 아니면 인코딩 처리를 해줘야 한다
		//그래서 꺼내기 전에 인코딩 세팅을 해줘야 한다 
		
		System.out.println("name => " + name);
		
		// 응답메시지 인코딩 설정
		resp.setCharacterEncoding("UTF-8");
		
		//콘솔에서 확인했을 때의 모양
		//Content-Type: text/plain;charset=UTF-8
		
		//사용자에게 html을 쏴줄건데, 인코딩 작업을 어디에?
		//그 문서에 포함된 캐릭터에 대해 UTF-8로 설정하겠다는 의미
		
		// 응답메시지 컨텐트 타입 설정
		resp.setContentType("text/plain");
		
		//콘솔에서 확인했을 때의 모양
		//Content-Type: text/plain;charset=UTF-8
		
		//콘텐트 타입 - 헤더에 포함
		//MIME타입: text/xml, text/html, image/jpg, image/png 등등
		
		// 실제 수행할 로직(기능)이 시작되는 부분...
		PrintWriter out = resp.getWriter();
		
		//바이트 기반이 아니고 문자기반의 writer를 들고오겠죠,
		//printoutputstream보다 printwriter가 더 문자기반 느낌이 난다
		//바이트 기반이라면 resp객체의 getOutputStream 사용하면 된다
		
		out.println("name => " + name);
		out.println("서블릿 경로 => " + req.getServletPath());
		out.println("컨텍스트 경로 => " + req.getContextPath());
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//전부 다 GET방식으로 처리하고 싶다면, doPost()메서드 내부에서 doGet()메서드 호출
		//우린 지금 메서드 방식과 무관하게 처리를 할 거다.
		doGet(req, resp); 
		
	}
	
}
