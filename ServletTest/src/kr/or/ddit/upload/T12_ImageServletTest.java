package kr.or.ddit.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(value = "/T12_ImageServletTest")
@WebServlet("/T12_ImageServletTest")
public class T12_ImageServletTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//컨텐츠 타입 설정
		resp.setContentType("image/jpg");
		
		ServletOutputStream out = resp.getOutputStream();
		
		FileInputStream fis = new FileInputStream("D:/D_Other/Tulips.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		//보조 스트림은 기반 스트림이 필요
		
		BufferedOutputStream bos = new BufferedOutputStream(out);
		//out으로 쏴줄 거니까, 쏴주면 나중에 톰캣이 리스폰스 메세지에 맞게 브라우저로 쏴준다
		
//		int readBytes = 0; //읽은 바이트수
//		while((readBytes = bis.read())!= -1) {
//			bos.write(readBytes);
//		}
		
		int readBytes = 0;
		while((readBytes = bis.read()) != -1) {
			bos.write(readBytes);
		}
		
		bis.close();
		bos.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
