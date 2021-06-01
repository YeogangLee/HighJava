package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T08_ServletFilter2 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("T08_ServletFilter2 => doFilter() 시작...");
		
		//서블릿 수행시간 계산하기
		long startTime = System.nanoTime();
		
		//필터 체인을 실행한다.(req, resp 객체 전달)
		fc.doFilter(req, resp);
		//리턴 이후 아래 코드 실행...
		
		System.out.println("수행시간(ns) : " + (System.nanoTime() - startTime));
		System.out.println("T08_ServletFilter2 => doFilter() 끝...");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
