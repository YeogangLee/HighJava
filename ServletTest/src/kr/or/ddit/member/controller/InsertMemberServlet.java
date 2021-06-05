package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class InsertMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/member/insertForm.jsp");
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
		
		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 3. 회원 등록 
		int cnt = memService.insertMember(mv);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		//여기까지 처리 끝, 그 다음은 어떻게?
		/*
		 	2가지 방법
		 	우리가 하고 싶은 건 목록을 이동하고 싶은 거죠
		 	화면으로 이동할 건데 아까처럼 디스패처 객체 이용 req.메서드
		 	 	
		 */
		
		// 4. 목록 조회화면으로 이동
		//포워드 방식
//		req.getRequestDispatcher("/member/list").forward(req, resp);
		
		//리다이렉트
		resp.sendRedirect(req.getContextPath() + "/member/list?msg=" + URLEncoder.encode(msg, "UTF-8"));
		
		//경로는 어떻게 설정할까요? 잘...
		//여기가 어디에요? 302 Location의 경로, 그걸 알려주는 거에요
		//redirect할 경로는 서버 기준 작성? 클라이언트 기준 작성?
		//너 여기로 올래?하고 알려주는 거
		
		//서버경로와 클라이언트 경로의 가장 큰 차이점
		//애플리케이션 경로를 모름녀 클라이언트에서는 접근 불가
		//외부에서 봤을 땐 <웹애플리케이션 루트>가 있어야 한다
		//1. 이 정보를 추가하냐 아니냐의 차이가 있다
		/*
		 => 경로에 컨텍스트 패스가 있어야 한다.
		 
		 한글이기 때문에 인코딩 시켜서 보내야 한다.
		 우리가 list.jsp에서 msg를 사용하고 있어서
		 저렇게 redirect할 때 파라미터로 넣어준건가?
		 만약에 안 쓰면 안 넣어줘도 되는 거고?
		 
		 */
	}
	
}
