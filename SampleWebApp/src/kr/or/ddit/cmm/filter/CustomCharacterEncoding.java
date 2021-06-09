package kr.or.ddit.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter를 이용한 한글 인코딩 처리 예제
 * @author PC-14
 *
 */
public class CustomCharacterEncoding implements Filter {
	
	//인코딩 정보 저장을 위한 전역변수
	private String encoding;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("인코딩 설정 정보 : " + this.encoding);
		req.setCharacterEncoding(this.encoding);
		resp.setCharacterEncoding(this.encoding);
		
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		if(config.getInitParameter("encoding") == null) {
			//기본 인코딩을 utf-8로 설정.(원래 기본 인코딩은 ISO-8859-1)
			//한글이 깨진 문서의 리스폰스 헤더에서 Content-Type을 살펴보면 저 인코딩 확인 가능
			this.encoding = "utf-8";
		}else {
			this.encoding = config.getInitParameter("encoding");
			//파라미터 "encoding"이 의미하는 것
			//=> web.xml에 encoding이라는 이름의 파라미터가 있다, 그것의 값을 가져오는 것
			//getInitParameter()메서드 이용  => <init-param>에 접근하겠다
			//encoding이라는 이름은 <init-param>내부의
			//<param-name>에 정의된 이름
			//이 이름을 이용해서
			//<param-value>에 정의된 값을 가져올 수 있다, getInitParameter(파라미터이름)이라고 했으니까
		}
		
	}

	
}
