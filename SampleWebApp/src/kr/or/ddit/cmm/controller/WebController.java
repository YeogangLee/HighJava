package kr.or.ddit.cmm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.cmm.handler.CommandHandler;

/**
 * 사용자 요청을 받아서 처리하는 대표 컨트롤러 구현하기
 * @author PC-14
 *
 */
public class WebController extends HttpServlet {

	//이제 System.out으로 안찍고 log4j로 로그로 디버깅
	private static Logger logger = Logger.getLogger(WebController.class);
	
	// 핸들러 매핑 정보 저장(key: 요청URL, value: 핸들러 객체)
	//핸들러 정보를 쭉 읽어와 Map에 꽂아주기
	private Map<String, CommandHandler> cmmHandlerMap = new HashMap<String, CommandHandler>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//실행이 되면, 특정 url이 오면 해당 url에 매핑되는 핸들러가 벌떡 일어나 작업 수행
		//별도의 properties파일로 매핑 할 것 - 이 커맨드는 이 핸들러, init()이라는 메서드는 초기화 수행시 한번만 수행
		//커맨드 객체 가져오려면 Map이니까 get()한다음에 키값 넣어주면 커맨드 객체 나오고, 그 커맨드객체.process()해서 작업 수행
		
	}	
	
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
		
	}
		
}
