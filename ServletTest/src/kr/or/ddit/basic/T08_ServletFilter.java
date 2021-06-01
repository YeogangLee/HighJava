package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter; //우리가 하려는 일과 관련된 Filter, 패키지 명을 보고 알 수 있다.
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//우리가 지금까지 만들어온 서블릿이 아니라 서블릿 필터이므로, 다른 클래스 extends가 아닌, 다른 인터페이스를 implements
public class T08_ServletFilter implements Filter {
/*
 	서블릿 필터에 대하여...
 	
 	1. 사용 목적
 	- 클라이언트의 요청을 수행하기 전에 가로채 필요한 작업을 수행할 수 있다.
 	- 클라이언트에 응답정보를 제공하기 전에 응답정보에 필요한 작업을 수행할 수 있다.
 	
 	2. 사용 예시
 	- 인증필터 (ex.로그인)
 	- 데이터 압축 필터 (ex.zip으로 response, 리스폰스를 필터에서 가로채서 압축해서 보내주기)
 	- 인코딩 필터 (리퀘스트 가져오기 전에 setCharacterEncoding, 가져오고 나서도 인코딩 설정, 필터에서 작업 해주면, 서블릿 단에서 안해도 된다.)
 	- 로깅 및 감사처리 필터 (서블릿 내에 로그를 찍는 코드를 삽입하지 않아도 된다, 인코딩필터도 마찬가지의 이유로 필터 사용)
 	- 이미지 변환 필터 등
 */
	
	@Override
	public void destroy() {
		//필터 객체가 웹 컨테이너에 의해 서비스로부터 제거되기 전에 호출됨.
		System.out.println("T08_ServletFilter => destroy() 호출됨.");
		
		/*
		 필터가 제거될 때 꼭 해야할 작업이 있다면, 그 작업의 수행을 위해 만들어 놓은 메서드
		 */
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException { //여기는 객체 앞에 Http가 없네..
		
		//요청이 들어올 때마다 요청을 가로채서, 정보를 출력하도록 하는 메서드 doFilter()
		System.out.println("T08_ServletFilter => doFilter() 시작...");
		System.out.println("접속IP : " + req.getRemoteAddr()
						+ "\n포트번호 : " + req.getRemotePort()
						+ "\n현재 시간 : " + new Date().toString());
		
		
		//필터 체인 실행 (req, resp 객체 전달)
		fc.doFilter(req, resp);
		//this찍는 거 뭐였더라... 모든 서블릿에 공통적으로 존재한 메서드
		
		//doFilter가 호출되는 시점에 필터 체인 객체를 넘겨 받는다
		//던져준 이유는? 필터 체인 내부의 doFilter()를 이용하기 위해, 같은 객체를 이용 - 리퀘스트, 리스폰스
		//필터 체인 내부의 필터 간에 doFilter()를 호출하면서, 다음 필터에게 객체를 전달한다.
		//제일 마지막 필터는 앞으로의 필터가 없으므로, doFilter()를 하면 서블릿에게 두 객체-리퀘스트,리스폰스-를 전달
		
		System.out.println("T08_ServletFilter => doFilter() 끝...");
		
		/*
		 여기 파라미터는 서블릿리퀘스트, http X, 서블릿 리퀘스트가 아니라 
		 캐스팅 해서 사용하면 된다
		 이 타입 자체에서 제공하는 메서드를 사용하려면 캐스팅 필요X
		 다른 프로토콜도 허용하기 위해, 상위에는
		 실제 들어오는 객체는 http프로토콜이다 => 적절히 캐스팅해서 사용
		 
		 파라미터로 받은 필터 객체로 doFilter()메서드 이용
		 한방에 처리
		 
		 */
	}

	/*
	 필터 객체 생성 후 딱 1번만 호출, 초기화 메서드
	 */
	@Override
	public void init(FilterConfig fc) throws ServletException {
		System.out.println("T08_ServletFilter => init() 호출됨.");
		
		//초기화 파라미터 정보 가져오기
		String initParam = fc.getInitParameter("init-param"); //web.xml에 작성한 이름, 필터 객체에 파라미터로 전달
		System.out.println("init-param : " + initParam);
	}
	
}
