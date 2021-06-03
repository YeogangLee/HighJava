package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class SelectAllMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회
		List<MemberVO> memList = memService.getAllMemberList();
		
		/*
		 리스폰스객체.get해주고 html 만들어주고... 콤마 하나하나 속성값 넣어준다
		 -> 너무 불편, 그래서 view는 jsp이용할 것
		 
		 JSP => MVC의 뷰View
		 mvc에서 데이터를 모델이라고 한다, 다오도 그렇고
		 
		 jsp는 html을 쉽게 만들어주는 기술, html에서 회원정보를 들고오는 것 - 데이터 -> 모델
		 내부적으로는 자바코드로 바뀌어서 서블릿으로 동작하게 된다, 나중에 까보면 알 수 있다
		 		 
		 memList왜 갖고 왔을까 -> jsp에서 꺼내 쓰기 위해
		 요청하고 응답하기 전에 보통 데이터를 리퀘스트에 저장한다,
		 리퀘스트 객체는 리스폰스하기 전까지 살아있으므로.
		 */
		
		
		//포워딩 작업 시작
		
		//3. View 페이지로 전달
		req.setAttribute("memList", memList); 
		
		//화면에 보여주려고 jsp에게 멤버 정보를 넘겨준다.
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/member/list.jsp");
		
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
