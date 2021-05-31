package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T01_ServletLifeCycle extends HttpServlet {
/*
	서블릿이란?
	
	컨테이너(서블릿 엔진)에 의해 관리되는 자바기반 웹 컴포넌트로서, 동적인 웹 컨텐츠 생성을 가능하게 해준다.
	
	//
	톰캣 말고도 다른 종류 많다 제우스도 있고 ... 하지만 무료로 사용하기 위해서는 톰캣
	서블릿 컨테이너 또는 서블릿 엔진 또는 WAS와스
	여기서 말하는 의미
	서블릿 스펙
	이 스펙을 기반으로 서블릿 클래스를 만들 거다
	서블릿 클래스는 그 자체로 하나의 웹 애플리케이션이 되는 거고
	
	컴포넌트: 한 번 만들어놓으면 다른 데서 재활용해서 사용 가능한 것
	
	servlet api tomcat 7 검색
	https://tomcat.apache.org/tomcat-7.0-doc/servletapi/index.html
	여기 나오는 스펙에 맞게 코딩
	
	서블릿 프로그래밍이라하면 굳이 말하지 않아도 HttpServlet을 의미
	기본적으로 베이스로 깔고 가는 프로토콜이 http
	http 프로토콜에서 동작하는 것들을 정리해놓은 게: Class HttpServlet
 */
	
	@Override
	public void init() throws ServletException {
		// 초기화 코드 작성...
		System.out.println("init() 호출됨...");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제적인 작업 수행이 시작되는 지점...(자바의 메인메서드 역할)
		System.out.println("service() 호출됨...");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 GET인 경우 호출됨...
		System.out.println("doGet() 호출됨...");
		
//		throw new ServletException("에러 심각!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 POST인 경우에 호출됨...
		System.out.println("doPost() 호출됨...");
	}
	
	@Override
	public void destroy() {
		// 객체 소멸시 (컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성...
		System.out.println("destroy() 호출됨...");
	}
	
}
