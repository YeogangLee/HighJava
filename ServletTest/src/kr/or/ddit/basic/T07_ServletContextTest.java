package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T07_ServletContextTest extends HttpServlet {
/*
    ServletContext
 	서블릿 컨텍스트 객체에 대하여...
 
 	1. 서블릿 프로그램이 컨테이너와 정보를 주고 받기 위한 메서드를 제공한다.
 	2. 웹 애플리케이션 당 1개씩 생성된다.
 	3. 서블릿이 생성되고 초기화 메서드 실행시,
 	   ServletConfig 객체를 통해서 얻을 수 있다.
 	   
 	
 	어떤 기능을 제공하는지 보면서 개념 정리 해보자.
 	
 	서블릿 프로그램
 	서블릿 1개만 얘기하는 게 아니고, 통합적인 관점에서, 웹 프로그래밍
 	
 	묶어진 war파일을 톰캣 컨테이너에 올려 서비스 제공 가능
 	
 	배포한 war파일 하나하나가 웹 애플리케이션, 서블릿 프로그램이 될 수 있는데
 	톰캣과 정보를 주고 받을 수 있는 객체 생성 -> ServletContext
 	서블릿 컨텍스트 객체는 결국 인터페이스
 	서블릿 컨텍스트 인터페이스를 구현한 객체는 모두 서블릿 컨텍스트 객체라고 부를 수 있다.
 	
 	서블릿이 톰캣 컨테이너에 로드되며 서비스 시작
 	
 	웹 애플리케이션 당 1개씩 생성 -> 다른 객체와 비교
 	HttpSession객체는 사용자별로 생성
 	사용자가 10명이면 10개 생성
 	ServletContext객체는 1개
 	톰캣에 애플리케이션 1개만 배포할 수 있는게 아니다
 	a.war, b.war이렇게 배포 가능
 	독자적으로 컨텍스트 루트가 구분되며, 실행 가능
 	2개의 홈페이지 제공
 	
 	각각의 홈페이지에 서블릿 컨텍스트 객체가 1개씩 만들어진다.
 	
 	지금 주석 달아놓은 것은 선생님께서 번역하신 것
 	파라미터가 있는 init()
 	파라미터로 ServletConfig config를 받는다
 	누가 줄까?
 	톰캣이
 	메서드 내부에서 config객체를 이용해서 서블릿 컨텍스트 객체를 들고올 수 있다
 	config.getServletContext()
 	
 	그런데
 	doGet()에서도
 	리퀘스트 객체의 getServletContext()메서드를 이용해서
 	서블릿 컨텍스트 객체를 들고올 수 있다
 	
 	서블릿 객체라면, getServletContext()이용 가능
 	서블릿을 대표하는 객체,
 	여러분들이 만든 애플리케이션을 대표
 	목적
 	컨테이너 관련된 세팅, 나 자신을 대표하는 객체니, 애플리케이션의 설정 정보 
 	다같이 바라보는 => 전역변수 느낌, 하나만 만들어서 다같이 공유
 	
 	언제까지 살아 있을까?
 	웹 애플리케이션 구동	 - 생성
 	웹 애플리케이션 구동 끝 - 소멸
 	
 	http서블릿 컨텍스트에 저장하는 게 좋다
 	웹 애플리케이션와 관련된 다같이 사용해야 하는 정보라면. 
 	
 	
 */	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext ctx = req.getServletContext();
		System.out.println("서블릿 컨텍스트의 기본 경로 : " + ctx.getContextPath());
		System.out.println("서버 정보 : " + ctx.getServerInfo());
		System.out.println("서블릿 api의 메이저 버전 정보 : " + ctx.getMajorVersion());
		System.out.println("서블릿 api의 마이너 버전 정보 : " + ctx.getMinorVersion());
		System.out.println("배포기술자에 기술된 컨텍스트명 : " + ctx.getServletContextName());
		System.out.println("리소스 경로 목록 : " + ctx.getResourcePaths("/"));
		System.out.println("모든 등록된 서블릿 목록 맵 : " + ctx.getServletRegistrations());
		System.out.println("모든 등록된 서블릿 목록 맵 toString: " + ctx.getServletRegistrations().toString());
		System.out.println("파일에 대한 MIME 타입 정보 : " + ctx.getMimeType("abc.mp3"));
		System.out.println("파일 시스템 상의 실제 경로 : " + ctx.getRealPath("/"));
		System.out.println("리소스의 URL 정보 : " + ctx.getResource("/index.html"));
		
		// 속성값 설정
		ctx.setAttribute("attr1", "속성1"); //키는 스트링, value는 오브젝트, 무엇이든 넣을 수 있다
		
		// 속성값 변경
		ctx.setAttribute("attr1", "속성2");
		
		// 속성값 가져오기
		System.out.println("attr1의 속성값 : " + ctx.getAttribute("attr1"));
		
		// 속성값 지우기
		ctx.removeAttribute("attr1");
		
		// 로깅 작업하기
		ctx.log("서블릿 컨텍스트를 이용한 로깅 작업 중입니다.");
		//로그 파일에도 똑같이 저장된다
		//이걸로 로깅을 많이 하지는 않는다, log4j등의 프레임워크를 사용하지, 이런 기능이 있다...
		
		// 포워딩 처리
//		ctx.getRequestDispatcher("/result.jsp").forward(req, resp);
		ctx.getRequestDispatcher("/T06_ServletSessionTest").forward(req, resp);
		
//		req.getRequestDispatcher("/T06_ServletSessionTest").forward(req, resp);
		
		//말그대로 RequestDispatcher를 들고오는데, request가 뭐에요? 요청
		// dispatch 파견하다, 보내다
		// dispatcher는? 보내기, 요청 보내기
		
		// 리퀘스트 정보는, 사용자의 모든 요청은 http서블릿리퀘스트 객체에 모두담겨 있다
		// 저기 forward()내부 파라미터
		// get했으니까 가지고 왔어 디스패치
		
		// forward가 뭔 뜻?
		// 여기서의 뜻: 전달하다
		// forward 사용 -> req, resp객체 들고오기
		// 모든 정보가 다 있다, resp하기 위해서도 resp객체 내부에 필요한 모든 정보가 다 있다
		/*
		 	저 파일에 jsp파일에 리퀘스트, 리스폰스 객체를 전달하겠다는 것
		 	html파일이 올 수 있고, 서블릿이 올 수도 있어요
		 	
		 	저 작업을 하게 된다면 req, resp객체를 전달 받는거죠
		 	
		 	doGet(req, resp) 이렇게 사용하면,
		 	호출한 곳과 호출한 메서드 내부에서 같은 객체를 사용하는 것
		 	
		 	화면 view단을 편하게 만들어주는 기술,
		 	우리가 사용하고 있는 객체를 forward()메서드로 전달을 하면서.
		 	
		 	서블릿 컨텍스트 객체는 서블릿을 줄여서
		 	Context라고 부르기도 한다.
		 	
		 	T06 입력 후 서버 실행
		 	T06페이지가 나타났지만, URL은 T07
		 	
		 	사용자 입장에서는, 마지막에 쏜 html화면을 보는 거지
		 	내부에서 어떤 forward() 과정이 일어났는지는 알 수 없다.
		 	forward() 특징: url의 변화가 없다.
		 	사용자가 요청한대로 url이 찍히고,
		 	단지 t06 서블릿이 처리한 것 뿐
		 	
		 	포워드의 내부 작업은 사용자 그리고 브라우저 또한 알지 못한다
		 	용도
		 	비즈니스 로직을 나눠서,
		 	mvc패턴으로 이해를 하면
		 	화면과 모델을 나눈다
		 	다오 통해 필요한 사용자 목록 자바 내부 > xml로 쿼리 분리
		 	
		 	사용자 목록 조회하는 컨트롤러가 받아서 다오에게 위임,
		 	이렇게 컨트롤러가 요청되는 서비스, 필요한 기능에 따라 다오에게 작업 위임
		 	
		 	=> 내부적으로는 포워드가 필요한 것, 진행되고 있는 것
		 	결과 자체는 리퀘스트에 저장을 하고
		 	모든 오브젝트, setAttribute로 저장하면 돼요
		 	
		 	html한땀한땀 만들기 싫으니까,
		 	jsp에게 html작업 위임, request객체로 넣어주면
		 	꺼내서 사용하면 되니까
		 	
		 	사용자 입장에서는 중간에 무슨 일이 일어나는지 알수없고 ...
		 	
		 	포워드 친구로 리다이렉트가 있다. redirect
		 	이건 나중에 직접 해보면서 설명	
		 	
		 	사용자와 서버의 세션이 유지되는 동안만 유지
		 	리퀘스트, 리스폰스는 리스폰스하면 끝 
		 	
		 	세션은 세션 종료 하면 끝, 브라우저 닫기
		 	
		 	가장 오래 남아있는 객체: ServletContext
		 	서블릿 구동 - 생성, 서버 종료 - 종료
		 	
		 	만약에 서블릿이 죽어도 살아있어야 하는 데이터라면?
		 	DB에 저장
		 	
		 	가급적이면
		 	값 저장을 지역변수에서 하는 것처럼, 메서드는 private으로 생성하는 것처럼
		 	=> 정보들은 리퀘스트 객체에 저장하는 게 좋다
		 	
		 	세션은 사용자별 구분이 가능한데, 서블릿 컨텍스트는 사용자별 개념이 없다, 누구든 접근 가능
		 	그러니 세션에 저장해야 할 정보를 컨텍스트에서는 저장할 수가 없는 것...
		 	
		 	
		 	- 서블릿 필터
		 	알아야 하는 이유
		 	서블릿을 지금까지 7개 정도 만들었는데...
		 	서블릿이라는 형태의 클래스를 이용해서, xml에 등록 후 서비스를 하고 있어요
		 	문제점
		 	예를 들면
		 	고객의 요청 - 사용자가 들어왔을 때, 서비스를 이용할 때마다 log를 db에 남기도록 코딩해주세요.
		 	단순히 생각하면 개발한 모든 소스를 까서,
		 	서비스 호출해서 객체를 가져다가, service.insertLog()같은 작업이 필요
		 	그러면 그 소스를 여러분들이 개발한 모든 서블릿에 집어넣어야 해요
		 	모든 서블릿에 넣었다가, 아니라고 하면 모두 뺐다가 => 비효율적, 그래서 필터 사용
		 	
		 	브라우저, 사용자
		 	브라우저	--- http프로토콜, 리퀘스트 --->	톰캣(서블릿 컨테이너)
		 			<--- http프로토콜, 리스폰스 ---
		 			
		 	톰캣 내부에서는
		 	서블릿 컨테이너 내부의 여러 서블릿들
		 	요청이 올 때마다 객체를 2개 만든다
		 	정보들을 객체에 잘 꽂아서, service()메서드가 호출되는 시점에 전달
		 	이 과정에서 필터가 하나 들어간다
		 	
		 	객체를 넘겨주기 전에
		 	사용자 요청을 한번 거른다
		 	
		 	리퀘스트, 리스폰스 객체가 필터에 접근 할 수 있어요
		 	이 필터에서 얻어지는데 => 요청 정보는 얻을 수 있다
		 	요청 url을 얻어서, db에 사용자 정보, 접근 로그를 필터에서 처리
		 	그러면 서블릿들은 이런 로그 정보를 가지고 있을 필요가 없고
		 	필터 내부의, 받은 요청 정보에서 서블릿을 호출
		 	반대로 끝나고 나면, 결과가 필터를 또 한번 거친다
		 	리스폰스 관련해서 처리할 게 있으면 필터 거치는 거고, 없으면 처리해줄 게 없는거고
		 	
		 	정확하게는 서블릿 필터
		 	
		 	이제 필터를 만들어보려고 한다.
		 	동시에 모든 서블릿들이 수행해야 하는 작업들을, 한방에 처리
		 	또 어떤 일 가능?
		 	
		 	게시판 상세보기 > 로그인한 사람만 보여줄래
		 	그러면 필터에서, 로그인했는지 안했는지 체크
		 	로직을 그렇게 구현을 해서, 로그인 안했으면 거기서 리스폰스
		 	로그인을 했으면 서블릿 불러서 작업 진행 
		 	
		 	//
		 	필터 체인 - 연결
		 	필터를 하나만 놓는 게 아니고, 여러 개를 놓을 수 있어요
		 	1번 필터, 2번 필터, 3번 필터
		 	제일 처음 요청 들어오면 1번 필터 거치고, 그 다음 필터, 그 다음 필터...
		 	모든 필터 이후에 서블릿에서 작업
		 	
		 	다시 돌아갈 때는 3번 -> 2번 -> 1번
		 	
			이렇게 필터들이 묶여있다고, 필터 체인이라고 부른다.
		 	
		 	필터 만들기: T08_ServletFilter	 
		 	
		 */
	
		//setAttribute(), removeAttribute(): 리퀘스트, 리스폰스, 세션, 여기 서블릿 컨텍스트 객체까지, 모두 존재
		//있을 거에요 찾아보세요.
		//MVC패턴에 있어서는 모델이죠, 모델 정보를 여기에 저장하면 돼요
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
