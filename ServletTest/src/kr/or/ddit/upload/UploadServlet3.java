package kr.or.ddit.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload3")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, maxFileSize = 1024 * 1024 * 40, maxRequestSize = 1024 * 1024 * 50)
public class UploadServlet3 extends HttpServlet {

	private static final String UPLOAD_DIRECTORY = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uploadPath = getServletContext().getRealPath("")
							+ File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			
			resp.setContentType("text/html");
			
			for(Part part : req.getParts()) {
				
				System.out.println(part.getHeader("content-disposition"));
				
				fileName = getFileName(part);
				
				//첨부파일이 존재
				//fileName.equals("")
				//이 조건은 왜?
				//첨부파일 자체는 있는데, 파일이 없는 경우, 엠프티라인이 될 수 있다 그런 경우를 위해 작성
				//타입 자체가 파일인 인풋 태그가 있을수도, 없을수도 있다
				//첨부파일이 있긴 있는데, 첨부를 안해서 파일이 없는 경우까지 체크하기 위해서
				if(fileName != null && !fileName.equals("")) {
					part.write(uploadPath + File.separator + fileName); //파일 저장
					resp.getWriter().print(fileName + " 업로드 완료");
					resp.getWriter().print(" / 전송자 : " + req.getParameter("sender"));
				}
				
			}		
			
			
		}catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("파라미터 값 : " + req.getParameter("sender"));
		
	}

	/**
	 * Part 객체 파싱하여 파일 이름 추출하기
	 * @param part
	 * @return 파일명 : 파일명이 존재하지 않으면 null값 리턴함(폼필드)
	 */
	private String getFileName(Part part) {
		
		//파일이라면 파일 이름이 있을 거고, 폼필드라면 파일명이 없으니 null값 반환
		/**
			Content-Disposition 헤더에 대하여...
			
			1. response header에 사용되는 경우... ex)파일 다운로드
			
			Content-Disposition: inline(default)
			Content-Disposition: attachment		//파일 다운로드 
			Content-Disposition: attachment; filename="name.jpg" // 해당 이름으로 다운로드
			
			2. multipart body를 위한 헤더정보로 사용되는 경우... ex)파일 업로드
			
			1/ Content-Disposition: form-data
			2/ Content-Disposition: form-data; name="fieldName"
			3/ Content-Disposition: form-data; name="fieldName"; filename="name.jpg"
			
			폼 필드값은 2/와같이 들어온다
			
		 */
		
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
				//트림으로 공백 제거, filename 여부에만 관심이 있으므로, if에 조건으로 작성
				//replace로 따옴표"를 공간이 없는 공백""으로 대체 => "제거
			}
		}
		
		return null; //filename이 없는 경우... (폼필드)
	}
}
