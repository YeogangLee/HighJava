package kr.or.ddit.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Orailly의 MultipartRequest를 이용한 파일 업로드 예제
 * (생성자 호출과 동시에 파일이 생성되기 때문에 선택적인 파일 생성 처리 불가)
 * @author PC-14
 *
 */
@WebServlet("/upload1")
public class UploadServlet1 extends HttpServlet {
	
	//선생님 PC IP : 192.168.42.149
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// MultipartRequest(request, 저장경로, [최대 허용 크기, 인코딩 캐릭터셋, 동일한 파일명 보호 여부])
		
		String encoding = "UTF-8";
		int maxFileSize = 5 * 1024 * 1024;
		
		MultipartRequest mr = new MultipartRequest(req, "d:/D_Other/",
				maxFileSize, encoding, new DefaultFileRenamePolicy());
		
		resp.setContentType("text/html");
		
		resp.getWriter().print("업로드 완료 : " + mr.getFile("fname"));
		
	}
}
