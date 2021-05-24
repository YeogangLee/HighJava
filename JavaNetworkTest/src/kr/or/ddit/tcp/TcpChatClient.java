package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;

public class TcpChatClient {
	public static void main(String[] args) {
		try {
			//얘도 소켓이 return될 때까지 block이 수행된다.
			Socket socket = new Socket("localhost", 7777); 
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();			
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
