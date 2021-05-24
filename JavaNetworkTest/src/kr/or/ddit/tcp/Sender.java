package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 소켓을 통해서 메시지를 보내는 역할 담당
 * @author PC-14
 *
 */
public class Sender extends Thread {
	private Scanner scan;
	private String name;
	private DataOutputStream dos;
	//필요한 객체를 멤버변수로 먼저 정의
	
	public Sender(Socket socket) {
		name = "[" + socket.getInetAddress() + " : "
				+ socket.getLocalPort() + "]";
		scan = new Scanner(System.in);
		
		try {
			//writeUTF()메서드 사용하려고 DataOutputStream 사용
			dos = new DataOutputStream(socket.getOutputStream());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		//null이 아닌 경우는 보통일 때, 그래서 무한루프
		while(dos != null) {
			try {
				//엔터를 칠 때마다 writeUTF()날라가고, 무한루프라서 다시 돌아와서 또 기다리고, 엔터칠때마다 계속 쏜다
				//엔터칠때마다 사용자에게 쏴주는 Sender를 만들어봤어요.
				dos.writeUTF(name + " >>> " + scan.nextLine());
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
