package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class UpdateMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//조회를 위한 select를 위한 memId값
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회
		MemberVO memVO = memService.getMember(memId);
		
		// 3. request객체에 회원정보 저장
		req.setAttribute("memVO", memVO);
		
		// 4. view 화면으로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/updateForm.jsp");
		
		//forward()안시키면 dispatcher밑에 노란줄
		
		dispatcher.forward(req, resp);
			
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		//정보 가져왔으면 업데이트 시켜주기 -> 서비스 객체 필요
		
		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//이제 회원정보 수정
		
		// 3. 회원정보 수정
		int cnt = memService.updateMember(mv);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";			
		}else {
			msg = "실패";
		}		
		
		// 4. 목록 조회화면으로 이동
		//포워드 방식
//		req.getRequestDispatcher("/member/list").forward(req, resp);
		
		//리다이렉트
		resp.sendRedirect(req.getContextPath() 
				+ "/member/list?msg=" + URLEncoder.encode(msg, "UTF-8"));
		
	}
}
