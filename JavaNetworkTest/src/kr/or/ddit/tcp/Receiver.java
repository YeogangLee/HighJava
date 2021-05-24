package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
	private DataInputStream dis;
	
	//소켓 없이는 통신을 할 수 없으니, 소켓을 파라미터로 받는다는 가정 하에 코딩을 한다.
	public Receiver(Socket socket) {
		try {
			//생성자에서 만든 이유는, Sender가 보낸 메시지를 readUTF()로 읽기 위해 객체 만들어놓은것
			dis = new DataInputStream(socket.getInputStream());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dis != null) {
			try {
				//읽을 게 있을 때까지 기다리다가, 콘솔에 println으로 한 줄 찍고, 다시 무한루프 돌고
				System.out.println(dis.readUTF());
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//채팅 프로그램은
	//서버 단도 이 기능이 있어야 하고
	//클라이언트 단도 이 기능이 있어야 해요
}
