package kr.or.ddit.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;

public class MultipartParser implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		try {
			// 요청래퍼 객체 생성
			FileUploadRequestWrapper reqWrapper = 
					new FileUploadRequestWrapper((HttpServletRequest)req);
			//제한을 걸거면 파라미터 6개로 된 생성자를 이용하면 되는데...
			
			//필터 인터페이스는 http서블릿리퀘스트보다 상위 인터페이스인
			//그냥 서블릿리퀘스트를 사용하므로, 파라미터에 형변환 캐스팅 작업 필요
			
			/*
			 오리지날 req를 넣는 게 아니고
			 reqWrapper를 넣는다, 얘도 http서블릿리퀘스트객체
			 멀티파트를 처리할 수 있는 기능을 갖춘 객체
			 reqWrapper : 이 객체를 이용하면
						   멀티파트라면 파싱 작업 처리
						   멀티파트가 아니라면 원래 리퀘스트 객체처럼 처리
						   
			 필터 작성 후 web.xml에 필터 등록
			 */
			
			// 래퍼객체 적용
			fc.doFilter(reqWrapper, resp);
			
		}catch(FileUploadException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
