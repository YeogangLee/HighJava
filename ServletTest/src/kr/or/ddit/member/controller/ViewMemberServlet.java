package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class ViewMemberServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//아까 파라미터로 넘겨준 값을 받기 위해 getParameter() 사용
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회
		MemberVO memVO = memService.getMember(memId);
		
		// 3. 결과 정보 담기
		req.setAttribute("memVO", memVO);
		
		// 4. VIEW 화면으로 이동
		req.getRequestDispatcher("/member/select.jsp").forward(req, resp);
		
		//서블릿은 톰캣 입장에서 바라보는 경로고
		//화면의 url은 브라우저 입장에서 바라보는 경로
		
		//서블릿에서는 지금처럼 애플리케이션의 루트부터 찾아들어가면 돼요
		//서블릿은 사용자는 전혀 모르는 세계, 백엔드 쪽에서 코딩을 하고 있으니까.
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
