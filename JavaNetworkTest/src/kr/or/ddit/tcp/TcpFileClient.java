package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpFileClient {
/*
 * 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 D:/C_Lib폴더에 저장한다.
 */
	private Socket socket;
	private InputStream in;
	private FileOutputStream fos;
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
	
	//클라이언트 시작
	public void clientStart() {
//		File file = new File("d:/C_Lib/Tulips_copy.jpg");
		File file = new File("d:/C_Lib/bo_left_copy.png");
		
		try {
			//얘도 block이 됐다가, 둘만의 소켓이 만들어지면 그 소켓을 받은 후 block이 풀린다
			socket = new Socket("192.168.42.149", 7777);
			System.out.println("파일 다운로드 시작...");
			
			fos = new FileOutputStream(file);
			in = socket.getInputStream();
			
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			int c = 0;
			while((c = bis.read())!= -1) {
				bos.write(c);
			}
			
			bis.close();
			bos.close();
			
			System.out.println("파일 다운로드 완료!");
			
			//클라이언트는 여기서 끝나지만, 서버는 서비스를 계속하기 때문에 무한루프를 돈다.
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			if(in != null) {
				try { in.close(); } catch(IOException ex) {}
			}
			if(fos != null) {
				try { fos.close(); } catch(IOException ex) {}
			}
			if(socket != null) {
				try { socket.close(); } catch(IOException ex) {}
			}
		}
		
	}
	
}
