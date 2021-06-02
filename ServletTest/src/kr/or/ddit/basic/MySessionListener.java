package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		System.out.println("[MySessionListener] attributeAdded() 호출됨 => "
							+ hsbe.getName() + " : " + hsbe.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		System.out.println("[MySessionListener] attributeRemoved() 호출됨 => "
							+ hsbe.getName() + " : " + hsbe.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		System.out.println("[MySessionListener] attributeReplaced() 호출됨 => "
							+ hsbe.getName() + " : " + hsbe.getValue());
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println("[MySessionListener] sessionCreated() 호출됨 => "
				+ "세션 ID : " +hse.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		System.out.println("[MySessionListener] sessionDestroyed() 호출됨 => "
				+ "세션 ID : " +hse.getSession().getId());
	}
	
}
