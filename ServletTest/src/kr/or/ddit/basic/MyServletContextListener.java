package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener {
	
	public MyServletContextListener() {
		//해당 클래스의 생성자
		System.out.println("[MyServletContextListener]"
						+ "MyServletContextListener()생성자 호출됨\t");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//서블릿 컨텍스트 객체가 초기화되는 시점에 수행
		System.out.println("[MyServletContextListener]"
						+ "contextInitialized()메서드 호출됨\t");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//서블릿 컨텍스트 객체가 소멸되는 시점에 수행
		System.out.println("[MyServletContextListener]"
						+ "contextDestroyed()메서드 호출됨\t");
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		//서블릿 컨텍스트 객체의 어트리뷰트가 추가됐을 때
		System.out.println("[MyServletContextListener]"
						+ "attributeAdded()메서드 호출됨\t" + scae.getName());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		//서블릿 컨텍스트 객체의 어트리뷰트가 삭제됐을 때
		System.out.println("[MyServletContextListener]"
						+ "attributeRemoved()메서드 호출됨\t"+ scae.getName());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		//서블릿 컨텍스트 객체의 어트리뷰트의 값이 변경됐을 때
		System.out.println("[MyServletContextListener]"
						+ "attributeReplaced()메서드 호출됨\t"+ scae.getName());
	}

}
