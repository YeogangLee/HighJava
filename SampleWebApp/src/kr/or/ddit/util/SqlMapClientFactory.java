package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * SqlMapClient 객체를 제공하는 팩토리 클래스 
 * @author PC-14
 *
 */
public class SqlMapClientFactory {
	private static SqlMapClient smc;	//SqlMapClient 객체 변수 선언
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			
			// iBatis를 이용하여 DB자료를 처리하는 작업 순서
			// 1. iBatis의 환경설정파일을 읽어와 실행시킨다.
			try {
				// 1-1. xml문서 읽어오기
				Charset charset = Charset.forName("UTF-8"); //설정 파일의 인코딩 설정
				Resources.setCharset(charset);
				
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close(); //Reader 객체 닫기
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		return smc;
	}
	
}
