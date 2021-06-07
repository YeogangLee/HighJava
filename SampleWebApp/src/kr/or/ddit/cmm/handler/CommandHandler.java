package kr.or.ddit.cmm.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	
	/**
	 * 해당 화면에 대해 포워드 및 리다이렉트 여부를 결정하기 위한 메서드
	 * @param req
	 * @return 리다이렉트인 경우 true, 아니면 false
	 */
	public boolean isRedirect(HttpServletRequest req);
	
	/**
	 * 요청한 내용을 실제 처리하는 메서드
	 * @param req 요청객체
	 * @param resp 응답객체
	 * @return 뷰페이지 정보 - 아래의 String 반환값, jsp위치
	 * @throws Exception
	 */
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
		
}
