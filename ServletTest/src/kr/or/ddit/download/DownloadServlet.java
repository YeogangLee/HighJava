package kr.or.ddit.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filedown")
public class DownloadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fileName = "Tulips.jpg";
		
		//파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
				
		/**
		Content-Disposition 헤더에 대하여...
		
		1. response header에 사용되는 경우... ex)파일 다운로드
		
		Content-Disposition: inline(default)
		Content-Disposition: attachment		//파일 다운로드 
		Content-Disposition: attachment; filename="name.jpg" // 해당 이름으로 다운로드
		
		지금까지 신경쓰지 않았다 -> 디폴트값으로 써왔다
		inline -> 브라우저 내부에서 보여주겠다
		아니 나는 다운로드 받을래! -> attachment 사용, 파일명 지정은 불가능
		다운로드 + 파일명 지정 	-> attachment; filename="name.jpg
		
		
		2. multipart body를 위한 헤더정보로 사용되는 경우... ex)파일 업로드
		
		1/ Content-Disposition: form-data
		2/ Content-Disposition: form-data; name="fieldName"
		3/ Content-Disposition: form-data; name="fieldName"; filename="name.jpg"
		
		
		폼 필드값은 2/와같이 들어온다
		
		*/
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:/D_Other/" + fileName));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int c = 0;
		while((c = bis.read()) != -1) {
			bos.write(c);
		}
		
		bis.close();
		bos.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
