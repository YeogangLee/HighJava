package kr.or.ddit.cmm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자 요청을 받아서 처리하는 대표 컨트롤러 구현하기
 * @author PC-14
 *
 */
@WebServlet("*.lee")
public class WebControllerWithoutCommandPattern extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);		
	}
	
	/**
	 * 요청 처리 메서드
	 * @param req
	 * @param resp
	 */
	private void process(HttpServletRequest req, HttpServletResponse resp) {
		
		//모든 url이 도착할 수 있으므로 방식을 분류해야 한다
		//리퀘스트 객체에서 URI정보 바로 갖다 쓰면 된다
		String reqURI = req.getRequestURI();
		
		System.out.println("reqURI : " + reqURI);
		
		//컨텍스트 패스 위치가 0 -> URI가 컨텍스트부터 시작
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(req.getContextPath().length());
		}
		
		System.out.println("reqURI substring() : " + reqURI);
		
		if(reqURI.equals("/member/list.do")) {
			// 목록 조회기능 처리
			
			
		}else if(reqURI.equals("/member/insert.do")) {
			// 등록 처리
			
			//getMethod(): get, post 구분
			if(req.getMethod().equals("GET")) {
				//등록하기 위한 폼 페이지 이동
				
			}else if(req.getMethod().equals("POST")) {
				//실제 등록 처리
				
			}
			
		}else if(reqURI.equals("/member/update.do")) {
			// 수정 처리
			
			//getMethod(): get, post 구분
			if(req.getMethod().equals("GET")) {
				//수정하기 위한 폼 페이지 이동
			}else if(req.getMethod().equals("POST")) {
				//실제 수정 처리
				
			}
		}
		
	/**
		Command 패턴에 대하여...
		
		정의 => 사용자 요청에 대한 실제 처리 기능을 커맨드 객체로 캡슐화하여 처리하는 패턴
		
		Command: 사용자 요청을 캡슐화한 객체(실제 처리 기능을 구현한 객체)
		Invoker: 사용자 요청에 대응되는 적당한 커맨드 객체를 찾아 실행해 주는 역할을 하는 객체
		
		- 장점
		: 요청을 수행하는 객체(Invoker)로부터 실제 수행처리 기능(Command)을 분리함으로써,
		   새로운 기능을 추가하는 데 보다 수월하다. (유지보수가 쉬워진다.)
		   
		invoke : 송장을 작성하다, 호소하다, 빌다
		    IT ) 호출한다 또는 시동한다는 의미로 명령, 절차 또는 프로그램을 호출하거나 시동하는 것 
	 */
		
		
		
	}
	
	
	
}






