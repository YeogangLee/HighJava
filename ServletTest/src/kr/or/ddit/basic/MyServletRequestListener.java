package kr.or.ddit.basic;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener{

	public MyServletRequestListener() {
		//생성자
		System.out.println("MyServletRequestListener 생성됨");
	}
	
	/* ServletRequestListener */
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		//초기화
		System.out.println("[MyServletRequestListener] requestDestroyed() 호출됨.");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		//소멸
		System.out.println("[MyServletRequestListener] requestInitialized() 호출됨.");
	}

	/* ServletRequestAttributeListener */
	
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] attributeAdded() 호출됨 => "
							+ srae.getName() + " : " + srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] attributeRemoved() 호출됨 => "
							+ srae.getName() + " : " + srae.getValue());		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("[MyServletRequestListener] attributeReplaced() 호출됨 =>"
							+ srae.getName() + " : " + srae.getValue());		
	}
	
	/*
		우리가 받는 객체는 http서블릿리퀘스트, 리스폰스 객체
		서블릿리퀘스트를 extends해서 http서블릿리퀘스트 객체를 만들었겠죠
		서블릿 리퀘스트가 더 상위 개념이니까
	 */
	
}
