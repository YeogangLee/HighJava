package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MySessionBindingListener implements HttpSessionBindingListener {

	/**
	 * Http 세션 영역 내에 HttpSessionBindingListener를 구현한 객체가 바인딩되면 호출됨.
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent hsbe) {
		String attrName = hsbe.getName();
		System.out.println("[MySessionBindingListener] valueBound() 호출됨  => "
							+ this.getClass().getName() + " 객체가 " + attrName + "으로 바인딩 됨.");
	}

	/**
	 * Http 세션 영역 내에 HttpSessionBindingListener를 구현한 객체가 언바인딩되면 호출됨.
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent hsbe) {
		String attrName = hsbe.getName();
		System.out.println("[MySessionBindingListener] valueUnbound() 호출됨  => "
							+ this.getClass().getName() + " 객체가 " + attrName + "으로 언!바인딩 됨.");

	}
	
}
