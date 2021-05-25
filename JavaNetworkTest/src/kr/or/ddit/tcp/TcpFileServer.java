package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
/*
 * 클라이언트가 접속하면 서버 컴퓨터의 D:/D_Other 폴더에 있는 Tulips.jpg 파일을 클라이언트로 전송한다.
 */
	
	private ServerSocket server;
	
	private Socket socket;
	private OutputStream out;
	private FileInputStream fis;
	
	private File file = new File("d:/D_Other/Tulips.jpg");
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
	
	//파일서버 시작
	public void serverStart() {
		while(true) {
			try {
				server = new ServerSocket(7777);
				System.out.println("서버 준비 완료...");
				
				socket = server.accept(); //클라이언트가  new로 접속해서, 소켓이 만들어지기 전까지 block
				System.out.println("파일 전송 시작...");
				
				fis = new FileInputStream(file);
				out = socket.getOutputStream();
				
				//보조스트림으로 buffer기능 활용, 1바이트씩 하면 너무 느리니까
				//버퍼 크기 지정 안해주면 기본 크기는 8바이트 8k바이트? 8k바이트 !!! 
				BufferedInputStream bis = new BufferedInputStream(fis);
				
				BufferedOutputStream bos = new BufferedOutputStream(out);
				
				int c = 0;
				while((c=bis.read()) != -1) {
					bos.write(c);
				}
				
				bis.close();
				bos.close();
				
				System.out.println("파일 전송 완료...");
				
			}catch(IOException ex) {
				ex.printStackTrace();
				
			}finally {
				if(fis != null) {
					try { fis.close(); } catch(IOException ex) {}
				}
				if(out != null) {
					try { out.close(); } catch(IOException ex) {}
				}
				if(socket != null) {
					try { socket.close(); } catch(IOException ex) {}
				}
				if(server != null) {
					try { server.close(); } catch(IOException ex) {}
				}
			}				
		}
	}
	
}
