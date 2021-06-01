package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T09_ServletContextListenerTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//어떤 리스너
		//여기서 변화를 줘야 우리가 볼 수 있다
//		this.getServletContext(); //이렇게 해서 서블릿 컨텍스트를 들고와도 되겠지만...
//		this.get

		getServletContext().setAttribute("ATTR1", "속성1");	//추가
		getServletContext().setAttribute("ATTR1", "속성11");	//변경
		getServletContext().setAttribute("ATTR2", "속성2");	//추가
		
		getServletContext().removeAttribute("ATTR1");		//제거		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
