package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
	public static void main(String[] args) {
		//서버 소켓을 만들고, 클라이언트가 접속하면 소켓을 만들어,
		//데이터를 받는 클래스와 데이터를 보내는 클래스에 이 소켓을 넘겨준다.
		//서버 소켓은 없으면 안돼요, 서버 소켓은 포트번호로 먼저 만들어서 accept()로 기다리고 있어야 해요
		//좀 전에 만들었던 Sender, Receiver 스레드 클래스 이용, 무한루프를 돌며 백그라운드에서 동작
		
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료...");
			socket = server.accept();
			//둘만의 소켓이 만들어지면, socket이 리턴되고, accept()의 block이 해제된다.
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			//메인 스레드는 이거 수행 후에 죽고, 나머지 2개 스레드만 살아서 프로그램을 동작시킨다.
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
