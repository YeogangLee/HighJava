package kr.or.ddit.member.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.handler.CommandHandler;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class ListMemberHandler2 implements CommandHandler {
	
//	private static final String VIEW_PAGE = "/member/list.jsp";
	private static final String VIEW_PAGE = "/WEB-INF/views/member/list.jsp";
	//member폴더를 이동했으므로 경로 변경

	// 1. 서비스 객체 생성
	IMemberService memService = MemberServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		//리스트는 jsp 보여주기만 하면 되므로 리다이렉트 안한다 -> 항상 false로 두기
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 2. 회원정보 조회
		List<MemberVO> memList = memService.getAllMemberList();
		
		//포워딩 작업 시작
		
//		//3. View 페이지로 전달
		req.setAttribute("memList", memList); 
//		
//		//화면에 보여주려고 jsp에게 멤버 정보를 넘겨준다.
//		RequestDispatcher dispatcher = 
//				req.getRequestDispatcher("/member/list.jsp");
//		
//		dispatcher.forward(req, resp);
		
		//웹 컨트롤러가 포워딩, 리다이렉트 작업을 수행해주므로
		//포워딩 작업을 수행하는 해당 위의 코드는 필요가 없다.
		
		//핸들러 내부에서 포워드, 리다이렉트 수행을 하지 않겠다는 룰 - command pattern
		//isRedirect() 메서드 사용 이유
		
		return VIEW_PAGE;
	}
	
}
